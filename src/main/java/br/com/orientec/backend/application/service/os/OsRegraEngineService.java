package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import br.com.orientec.backend.application.port.out.UsuarioPermissionPort;
import br.com.orientec.backend.application.port.out.UsuarioPersistencePort;
import br.com.orientec.backend.application.port.out.os.*;
import br.com.orientec.backend.security.JwtService;
import br.com.orientec.backend.application.service.idempotency.IdempotencyService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OsRegraEngineService {
    private final OrdemServicoPersistencePort osRepo;
    private final OsStatusPersistencePort statusRepo;
    private final RegraTransicaoPersistencePort regras;
    private final OrdemServicoHistoricoPersistencePort historico;
    private final UsuarioPersistencePort usuarios;
    private final UsuarioPermissionPort permissoes;
    private final PasswordEncoder encoder;
    private final IdempotencyService idempotency;
    private final RegraCondicaoPersistencePort condicoes;

    public OsRegraEngineService(
            OrdemServicoPersistencePort osRepo,
            OsStatusPersistencePort statusRepo,
            RegraTransicaoPersistencePort regras,
            OrdemServicoHistoricoPersistencePort historico,
            UsuarioPersistencePort usuarios,
            UsuarioPermissionPort permissoes,
            PasswordEncoder encoder,
            IdempotencyService idempotency,
            RegraCondicaoPersistencePort condicoes) {

        this.osRepo = osRepo;
        this.statusRepo = statusRepo;
        this.regras = regras;
        this.historico = historico;
        this.usuarios = usuarios;
        this.permissoes = permissoes;
        this.encoder = encoder;
        this.idempotency = idempotency;
        this.condicoes = condicoes;
    }

    @Transactional(readOnly=true)
    public List<br.com.orientec.backend.adapter.in.web.dto.os.OsAcaoResponse> acoes(Long osId, Long usuarioCodigo) {
        OrdemServico os = osRepo.findById(osId).orElseThrow(() -> new IllegalArgumentException("OS não encontrada."));
        Usuario usuario = usuarios.findById(usuarioCodigo).filter(Usuario::isAtivo).orElseThrow(() -> new IllegalArgumentException("Usuário inválido ou inativo."));
        Set<String> perms = permissoes.findCodigosByUsuario(usuarioCodigo);
        return regras.findByOrigemIdAndAtivaTrue(os.getSituacao().getId()).stream().map(r -> {
            boolean nivel = r.getMinNivel()==null || (usuario.getNivelCodigo()!=null && usuario.getNivelCodigo() >= r.getMinNivel());
            boolean execPerm = r.getPermissaoExecucao()==null || perms.contains(r.getPermissaoExecucao());
            boolean allowed = nivel && execPerm;
            String msg = allowed ? (r.isExigeAutorizacao() ? "Disponível mediante liberação." : "Permitida.") : "Sem autorização para executar esta regra.";
            OsStatus d = statusRepo.findById(r.getDestinoId()).orElse(null);
            return new br.com.orientec.backend.adapter.in.web.dto.os.OsAcaoResponse(r.getTipoTransicao(), r.getDestinoId(), d==null?null:d.getCodigo(), d==null?null:d.getDescricao(), allowed, r.isExigeAutorizacao(), r.getPermissaoExecucao(), r.getPermissaoAutorizacao(), r.isExigeOutroUsuario(), r.getMetodoAutorizacao(), msg);
        }).toList();
    }

    @Transactional
    public void executar(Long osId, Short destinoId, Long solicitante, Long autorizador, String senha, String justificativa, String idempotencyKey) {
        String fingerprint = osId + "|" + destinoId + "|" + solicitante + "|" + autorizador + "|" + (justificativa==null?"":justificativa);
        if (!idempotency.begin(idempotencyKey, fingerprint)) return;
        OrdemServico os = osRepo.findByIdForUpdate(osId).orElseThrow(() -> new IllegalArgumentException("OS não encontrada."));
        RegraTransicaoOs regra = regras.findByOrigemIdAndDestinoIdAndAtivaTrue(os.getSituacao().getId(), destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Transição não cadastrada para esta situação."));
        Usuario user = usuarios.findById(solicitante).filter(Usuario::isAtivo).orElseThrow(() -> new IllegalArgumentException("Solicitante inválido ou inativo."));
        Set<String> perms = permissoes.findCodigosByUsuario(solicitante);
        if (regra.getMinNivel()!=null && (user.getNivelCodigo()==null || user.getNivelCodigo() < regra.getMinNivel())) throw new IllegalArgumentException("Nível de acesso insuficiente para esta transição.");
        var conds = condicoes.findByRegraTransicaoIdAndAtivoTrueOrderByOrdem(regra.getId());
        if (!conds.isEmpty()) throw new IllegalArgumentException("A transição possui condições configuradas que ainda não foram implementadas no motor: " + conds.stream().map(c -> c.getCampo()+" "+c.getOperador()).reduce((a,b)->a+", "+b).orElse(""));
        if (regra.getPermissaoExecucao()!=null && !perms.contains(regra.getPermissaoExecucao())) throw new IllegalArgumentException("Usuário não possui " + regra.getPermissaoExecucao() + ".");
        if (regra.isExigeAutorizacao()) {
            if (autorizador==null || (regra.isExigeOutroUsuario() && autorizador.equals(solicitante))) throw new IllegalArgumentException("Esta transição exige outro acesso para liberação.");
            Usuario auth=usuarios.findById(autorizador).filter(Usuario::isAtivo).orElseThrow(() -> new IllegalArgumentException("Autorizador inválido ou inativo."));
            if (regra.getPermissaoAutorizacao()!=null && !permissoes.findCodigosByUsuario(autorizador).contains(regra.getPermissaoAutorizacao())) throw new IllegalArgumentException("Autorizador não possui " + regra.getPermissaoAutorizacao() + ".");
            if ("SENHA_OUTRO_ACESSO".equals(regra.getMetodoAutorizacao()) && (senha==null || !encoder.matches(senha, auth.getSenhaHash()))) throw new IllegalArgumentException("Credencial do autorizador inválida.");
            if (justificativa==null || justificativa.isBlank()) throw new IllegalArgumentException("Justificativa é obrigatória para esta liberação.");
        }
        Short anterior=os.getSituacao().getId();
        OsStatus destino=statusRepo.findById(destinoId).filter(OsStatus::isAtivo).orElseThrow(() -> new IllegalArgumentException("Situação destino inválida ou inativa."));
        os.mudarSituacao(destino);
        osRepo.save(os);
        historico.save(new OrdemServicoHistorico(osId,destinoId,solicitante,"TRANSICAO_OS", "Situação " + anterior + " -> " + destinoId + (justificativa==null?"":" | " + justificativa)));
        idempotency.complete(idempotencyKey, fingerprint, 204, "");
    }
}

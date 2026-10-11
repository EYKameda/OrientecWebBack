package br.com.orientec.backend.application.service.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.service.os.*;
import br.com.orientec.backend.application.port.out.os.*;
import br.com.orientec.backend.application.port.out.UsuarioPersistencePort;
import br.com.orientec.backend.application.port.out.UsuarioPermissionPort;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.application.service.idempotency.IdempotencyService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RetrocessoService {
    private final OrdemServicoPersistencePort osRepository;
    private final RegraTransicaoPersistencePort regras;
    private final OsStatusPersistencePort statusRepository;
    private final UsuarioPersistencePort usuarios;
    private final UsuarioPermissionPort permissoes;
    private final OrdemServicoRetrocessoPersistencePort retrocessos;
    private final OrdemServicoHistoricoPersistencePort historico;
    private final PasswordEncoder encoder;
    private final IdempotencyService idempotency;

    public RetrocessoService(OrdemServicoPersistencePort osRepository, RegraTransicaoPersistencePort regras,
                             OsStatusPersistencePort statusRepository, UsuarioPersistencePort usuarios,
                             UsuarioPermissionPort permissoes,
                             OrdemServicoRetrocessoPersistencePort retrocessos,
                             OrdemServicoHistoricoPersistencePort historico,
                             PasswordEncoder encoder, IdempotencyService idempotency) {
        this.osRepository=osRepository; this.regras=regras; this.statusRepository=statusRepository;
        this.usuarios=usuarios; this.permissoes=permissoes; this.retrocessos=retrocessos;
        this.historico=historico; this.encoder=encoder; this.idempotency=idempotency;
    }

    @Transactional
    public void retroceder(Long osId, Short destinoId, Long solicitante,
                           Long autorizador, String senhaAutorizador, String justificativa,
                           String idempotencyKey) {

        if (solicitante == null || autorizador == null || destinoId == null || justificativa == null)
            throw new IllegalArgumentException("Dados obrigatórios do retrocesso não informados.");

        String fingerprint = osId + "|" + destinoId + "|" + solicitante + "|" +
            autorizador + "|" + justificativa.trim();

        if (!idempotency.begin(idempotencyKey, fingerprint)) {
            return;
        }

        if (solicitante == null || autorizador == null || solicitante.equals(autorizador))
            throw new IllegalArgumentException("Solicitante e autorizador devem ser diferentes.");
        if (senhaAutorizador == null || senhaAutorizador.isBlank())
            throw new IllegalArgumentException("Senha do autorizador é obrigatória.");
        if (justificativa == null || justificativa.isBlank())
            throw new IllegalArgumentException("Justificativa do retrocesso é obrigatória.");

        usuarios.findById(solicitante).filter(Usuario::isAtivo)
            .orElseThrow(() -> new IllegalArgumentException("Solicitante inválido ou inativo."));

        var auth = usuarios.findById(autorizador).filter(Usuario::isAtivo)
            .orElseThrow(() -> new IllegalArgumentException("Autorizador inválido ou inativo."));

        if (!encoder.matches(senhaAutorizador, auth.getSenhaHash()))
            throw new IllegalArgumentException("Credencial do autorizador inválida.");

        if (!permissoes.findCodigosByUsuario(autorizador).contains("OS_RETROCEDER"))
            throw new IllegalArgumentException("Autorizador não possui OS_RETROCEDER.");

        var os = osRepository.findByIdForUpdate(osId)
            .orElseThrow(() -> new IllegalArgumentException("OS não encontrada."));

        Short origem = os.getSituacao().getId();
        var regra = regras.findByOrigemIdAndDestinoIdAndAtivaTrue(origem, destinoId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Transição não permitida: " + origem + " -> " + destinoId));

        if (!regra.isRetrocesso())
            throw new IllegalArgumentException("A transição não está configurada como retrocesso.");

        var destino = statusRepository.findById(destinoId)
            .orElseThrow(() -> new IllegalArgumentException("Destino inexistente."));

        os.mudarSituacao(destino);

        retrocessos.save(new OrdemServicoRetrocesso(
            osId, origem, destinoId, solicitante, autorizador, justificativa.trim()));

        historico.save(new OrdemServicoHistorico(
            osId, destinoId, solicitante, "RETROCESSO",
            "Retrocesso autorizado; origem=" + origem +
            "; destino=" + destinoId +
            "; autorizador=" + autorizador));

        idempotency.complete(idempotencyKey, fingerprint, 204, "");
    }
}

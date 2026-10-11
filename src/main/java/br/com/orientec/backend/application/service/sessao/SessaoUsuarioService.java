package br.com.orientec.backend.application.service.sessao;

import br.com.orientec.backend.adapter.out.persistence.entity.sessao.SessaoUsuario;
import br.com.orientec.backend.adapter.out.persistence.repository.sessao.SessaoUsuarioRepository;
import br.com.orientec.backend.adapter.out.persistence.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.Duration;
import java.util.UUID;

@Service
public class SessaoUsuarioService {
    private final SessaoUsuarioRepository sessoes;
    private final UsuarioRepository usuarios;
    private final SessaoEstacaoService estacoes;
    public SessaoUsuarioService(SessaoUsuarioRepository sessoes, UsuarioRepository usuarios, SessaoEstacaoService estacoes){this.sessoes=sessoes;this.usuarios=usuarios;this.estacoes=estacoes;}

    @Transactional
    public SessaoUsuario criar(Long usuarioCodigo, UUID sessaoEstacaoId, int maxMinutos) {
        estacoes.validar(sessaoEstacaoId);
        var agora=now();
        var s=SessaoUsuario.nova(); s.setId(UUID.randomUUID()); s.setUsuarioCodigo(usuarioCodigo); s.setSessaoEstacaoId(sessaoEstacaoId); s.setCriadaEm(agora); s.setUltimoAcessoEm(agora); s.setExpiraEm(agora.plusMinutes(maxMinutos));
        return sessoes.save(s);
    }

    @Transactional
    public void validarEAtualizar(UUID sessaoId) {
        var s=sessoes.findById(sessaoId).orElseThrow(()->new SessionAccessException("SESSAO_INEXISTENTE","Sessão do usuário inexistente."));
        var agora=now();
        if(s.getEncerradaEm()!=null) throw new SessionAccessException("SESSAO_ENCERRADA","A sessão do usuário foi encerrada.");
        if(agora.isAfter(s.getExpiraEm())) { encerrar(s,"TEMPO_MAXIMO_SESSAO"); throw new SessionAccessException("TEMPO_MAXIMO_SESSAO","O tempo máximo de sessão foi atingido."); }
        var u=usuarios.findById(s.getUsuarioCodigo()).orElseThrow(()->new SessionAccessException("USUARIO_INEXISTENTE","Usuário não encontrado."));
        if(!u.isAtivo()) { encerrar(s,"USUARIO_INATIVO"); throw new SessionAccessException("USUARIO_INATIVO","Usuário inativo."); }
        if(agora.isAfter(s.getUltimoAcessoEm().plusMinutes(u.getTempoOciosoMinutos()))) { encerrar(s,"TEMPO_OCIOSO"); throw new SessionAccessException("TEMPO_OCIOSO","A sessão expirou por inatividade."); }
        try { estacoes.validar(s.getSessaoEstacaoId()); } catch (SessaoEstacaoService.StationAccessException ex) { encerrar(s,"SESSAO_ESTACAO_EXPIRADA"); throw new SessionAccessException("SESSAO_ESTACAO_EXPIRADA","A sessão da estação expirou."); }
        s.setUltimoAcessoEm(agora); sessoes.save(s);
    }

    @Transactional
    public void encerrar(UUID sessaoId,String motivo){sessoes.findById(sessaoId).ifPresent(s->{if(s.getEncerradaEm()==null){s.setEncerradaEm(now());s.setMotivoEncerramento(motivo);sessoes.save(s);}});}
    private void encerrar(SessaoUsuario s,String motivo){s.setEncerradaEm(now());s.setMotivoEncerramento(motivo);sessoes.save(s);}
    private OffsetDateTime now(){return OffsetDateTime.now(ZoneOffset.UTC);}
    public static class SessionAccessException extends RuntimeException { public final String code; public SessionAccessException(String code,String message){super(message);this.code=code;} }
}

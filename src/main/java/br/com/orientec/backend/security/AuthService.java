package br.com.orientec.backend.security;

import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import br.com.orientec.backend.application.port.out.UsuarioAuthPort;
import br.com.orientec.backend.application.port.out.UsuarioPermissionPort;
import br.com.orientec.backend.application.service.sessao.SessaoEstacaoService;
import br.com.orientec.backend.application.service.sessao.SessaoUsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService {
    private final UsuarioAuthPort usuarios;
    private final UsuarioPermissionPort permissoes;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final SessaoEstacaoService estacoes;
    private final SessaoUsuarioService sessoes;

    public AuthService(UsuarioAuthPort usuarios, UsuarioPermissionPort permissoes, PasswordEncoder encoder, JwtService jwt,
                       SessaoEstacaoService estacoes, SessaoUsuarioService sessoes) {
        this.usuarios=usuarios; this.permissoes=permissoes; this.encoder=encoder; this.jwt=jwt; this.estacoes=estacoes; this.sessoes=sessoes;
    }

    @Transactional
    public LoginResult login(String login, String senha, UUID sessaoEstacaoId) {
        try { estacoes.validar(sessaoEstacaoId); } catch (RuntimeException e) { throw e; }
        var u = usuarios.findByLoginIgnoreCase(login).orElseThrow(() -> new BadCredentialsException("Usuário ou senha inválidos."));
        if (!u.isAtivo() || !encoder.matches(senha, u.getSenhaHash())) throw new BadCredentialsException("Usuário ou senha inválidos.");
        Long sedeEstacao=estacoes.sedeCodigo(sessaoEstacaoId);
        if (u.getSedeCodigo() == null || !u.getSedeCodigo().equals(sedeEstacao))
            throw new BadCredentialsException("Este usuário não está na Sede correta, verifique.");
        var session=sessoes.criar(u.getCodigo(),sessaoEstacaoId,u.getTempoMaximoSessaoMinutos());
        var token=jwt.issue(u.getCodigo(),u.getLogin(),u.getSedeCodigo(),permissoes.findCodigosByUsuario(u.getCodigo()),session.getId(),u.getTempoMaximoSessaoMinutos());
        return new LoginResult(token,session.getId(),session.getExpiraEm());
    }

    @Transactional
    public void logout(UUID sessionId){sessoes.encerrar(sessionId,"LOGOUT");}
    public record LoginResult(String accessToken, UUID sessionId, java.time.OffsetDateTime expiresAt) {}
}

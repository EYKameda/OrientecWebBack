package br.com.orientec.backend.security;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/administrativo")
public class AdministrativoController {
    private final JdbcTemplate jdbc;
    private final PasswordEncoder encoder;
    private final SecurityPolicyService policy;
    private final AdministrativeSessionService sessions;

    public AdministrativoController(JdbcTemplate jdbc, PasswordEncoder encoder,
            SecurityPolicyService policy, AdministrativeSessionService sessions) {
        this.jdbc = jdbc;
        this.encoder = encoder;
        this.policy = policy;
        this.sessions = sessions;
    }

    @org.springframework.context.event.EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void initializeSingleRecord() {
        var rows = jdbc.query("SELECT senha FROM seguranca WHERE id=1",
            (rs, n) -> rs.getString("senha"));
        if (rows.isEmpty()) {
            jdbc.update("INSERT INTO seguranca(id, login, senha, orientec_dev_mode) VALUES (1, 'admin', ?, TRUE)",
                encoder.encode("admin"));
        } else if ("!INICIALIZAR_BCRYPT_ADMIN!".equals(rows.get(0))) {
            jdbc.update("UPDATE seguranca SET senha=?, atualizado_em=CURRENT_TIMESTAMP WHERE id=1",
                encoder.encode("admin"));
        }
    }

    @GetMapping("/modo")
    public ModeResponse mode() {
        return new ModeResponse(!policy.isSecurityRequired(), policy.isSecurityRequired());
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var rows = jdbc.query("SELECT login, senha, orientec_dev_mode FROM seguranca WHERE id=1",
            (rs, n) -> new SecurityRow(rs.getString("login"), rs.getString("senha"), rs.getBoolean("orientec_dev_mode")));
        if (rows.isEmpty() || !rows.get(0).login().equals(request.login().trim())
                || !encoder.matches(request.senha(), rows.get(0).senhaHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou senha administrativa inválidos.");
        }
        return new LoginResponse(sessions.create(rows.get(0).login()), rows.get(0).login(), rows.get(0).devMode());
    }

    @PutMapping("/configuracao")
    public ModeResponse update(@RequestHeader(value="Authorization", required=false) String authorization,
                               @Valid @RequestBody UpdateRequest request) {
        String token = authorization != null && authorization.startsWith("Bearer ")
            ? authorization.substring(7) : null;
        String sessionLogin = sessions.validate(token);
        if (sessionLogin == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão administrativa expirada. Entre novamente.");
        var rows = jdbc.query("SELECT login, senha FROM seguranca WHERE id=1",
            (rs, n) -> new SecurityRow(rs.getString("login"), rs.getString("senha"), false));
        if (rows.isEmpty() || !sessionLogin.equals(rows.get(0).login())) {
            sessions.revoke(token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "A credencial administrativa foi alterada. Entre novamente.");
        }
        if (request.login().trim().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O login é obrigatório.");
        if (request.senhaAtual() == null || request.senhaAtual().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha atual é obrigatória.");
        }
        if (!encoder.matches(request.senhaAtual(), rows.get(0).senhaHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "A senha atual está incorreta.");
        }
        if (request.novaSenha() == null || request.novaSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova senha é obrigatória.");
        }
        if (!request.novaSenha().equals(request.confirmacaoNovaSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A confirmação da nova senha não confere.");
        }
        String newHash = encoder.encode(request.novaSenha());
        try {
            jdbc.update("UPDATE seguranca SET login=?, senha=?, orientec_dev_mode=?, atualizado_em=CURRENT_TIMESTAMP WHERE id=1",
                request.login().trim(), newHash, request.orientecDevMode());
        } catch (DuplicateKeyException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O login informado já está em uso.");
        }
        policy.refresh();
        sessions.revoke(token);
        return mode();
    }

    private record SecurityRow(String login, String senhaHash, boolean devMode) {}
    public record LoginRequest(@NotBlank String login, @NotBlank String senha) {}
    public record UpdateRequest(
        @NotBlank String login,
        @NotBlank String senhaAtual,
        @NotBlank String novaSenha,
        @NotBlank String confirmacaoNovaSenha,
        @NotNull Boolean orientecDevMode) {}
    public record LoginResponse(String token, String login, boolean orientecDevMode) {}
    public record ModeResponse(boolean orientecDevMode, boolean securityRequired) {}
}

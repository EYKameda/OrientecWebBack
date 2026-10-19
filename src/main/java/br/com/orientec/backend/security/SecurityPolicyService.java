package br.com.orientec.backend.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SecurityPolicyService {
    private final JdbcTemplate jdbc;
    private final AtomicBoolean devMode = new AtomicBoolean(false);
    private volatile List<SimpleGrantedAuthority> cachedAuthorities = List.of();

    public SecurityPolicyService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @org.springframework.context.event.EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void load() { refresh(); }

    public synchronized void refresh() {
        try {
            Boolean value = jdbc.queryForObject(
                "SELECT orientec_dev_mode FROM seguranca WHERE id=1", Boolean.class);
            devMode.set(Boolean.TRUE.equals(value));
        } catch (RuntimeException ex) {
            // Fail closed: missing or unreadable configuration requires authentication.
            devMode.set(false);
        }
        try {
            cachedAuthorities = jdbc.queryForList(
                "SELECT codigo FROM permissao WHERE ativo=TRUE", String.class)
                .stream().map(p -> new SimpleGrantedAuthority("PERM_" + p)).toList();
        } catch (RuntimeException ex) {
            cachedAuthorities = List.of(
                new SimpleGrantedAuthority("PERM_OS_CONSULTAR"),
                new SimpleGrantedAuthority("PERM_OS_ABRIR"),
                new SimpleGrantedAuthority("PERM_OS_ORCAMENTO"),
                new SimpleGrantedAuthority("PERM_OS_APROVAR"),
                new SimpleGrantedAuthority("PERM_OS_EXECUTAR"),
                new SimpleGrantedAuthority("PERM_OS_ENTREGAR"),
                new SimpleGrantedAuthority("PERM_OS_RETROCEDER"),
                new SimpleGrantedAuthority("PERM_OS_CONTROLAR"),
                new SimpleGrantedAuthority("PERM_OS_AUTORIZAR_EXCEPCIONAL"));
        }
    }

    public boolean isDevModeActive() { return devMode.get(); }
    public boolean isSecurityRequired() { return !devMode.get(); }
    public boolean authenticationDisabled() { return devMode.get(); }
    public List<SimpleGrantedAuthority> developmentAuthorities() { return cachedAuthorities; }
}

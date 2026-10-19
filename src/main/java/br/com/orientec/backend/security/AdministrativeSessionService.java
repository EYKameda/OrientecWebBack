package br.com.orientec.backend.security;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdministrativeSessionService {
    private static final long TTL_SECONDS = 15 * 60;
    private final SecureRandom random = new SecureRandom();
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    public String create(String login) {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        sessions.put(token, new Session(login, Instant.now().plusSeconds(TTL_SECONDS)));
        return token;
    }

    public String validate(String token) {
        if (token == null || token.isBlank()) return null;
        Session s = sessions.get(token);
        if (s == null) return null;
        if (Instant.now().isAfter(s.expiresAt())) {
            sessions.remove(token);
            return null;
        }
        return s.login();
    }

    public void revoke(String token) { if (token != null) sessions.remove(token); }
    private record Session(String login, Instant expiresAt) {}
}

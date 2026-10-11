package br.com.orientec.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {
    private final SecretKey key; private final Duration configuredTtl;
    public JwtService(@Value("${security.jwt.secret}") String secret,@Value("${security.jwt.ttl:PT24H}") Duration ttl){
        if(secret.getBytes(StandardCharsets.UTF_8).length<32) throw new IllegalArgumentException("JWT secret deve ter pelo menos 32 bytes.");
        key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); configuredTtl=ttl;
    }
    public String issue(Long codigo,String login,Long sedeCodigo,Set<String> permissoes,UUID sessaoId,int maxSessionMinutes){
        Instant now=Instant.now(); Duration max=Duration.ofMinutes(maxSessionMinutes); Duration effective=max.compareTo(configuredTtl)<0?max:configuredTtl;
        return Jwts.builder().subject(login).claim("usuarioCodigo",codigo).claim("sedeCodigo",sedeCodigo).claim("permissoes",permissoes).claim("sessaoId",sessaoId.toString()).issuedAt(Date.from(now)).expiration(Date.from(now.plus(effective))).signWith(key).compact();
    }
    public JwtPrincipal parse(String token){
        var c=Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        Long codigo=c.get("usuarioCodigo",Long.class); Long sede=c.get("sedeCodigo",Long.class); String login=c.getSubject();
        Object raw=c.get("permissoes"); Set<String> permissions=raw instanceof Collection<?> col?col.stream().map(String::valueOf).collect(java.util.stream.Collectors.toUnmodifiableSet()):Set.of();
        UUID sessao=UUID.fromString(c.get("sessaoId",String.class));
        return new JwtPrincipal(codigo,login,sede,permissions,sessao);
    }
    public record JwtPrincipal(Long usuarioCodigo,String login,Long sedeCodigo,Set<String> permissoes,UUID sessaoId){}
}

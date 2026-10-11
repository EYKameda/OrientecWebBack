package br.com.orientec.backend.security;

import br.com.orientec.backend.application.service.sessao.SessaoEstacaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService service; private final JwtService jwt;
    public AuthController(AuthService service, JwtService jwt){this.service=service;this.jwt=jwt;}
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request,HttpServletRequest http){
        var r=service.login(request.login(),request.senha(),stationCookie(http));
        return new LoginResponse(r.accessToken(),r.sessionId(),r.expiresAt());
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest http){
        String h=http.getHeader("Authorization");
        if(h!=null&&h.startsWith("Bearer ")) try{service.logout(jwt.parse(h.substring(7)).sessaoId());}catch(Exception ignored){}
    }
    private UUID stationCookie(HttpServletRequest request){
        if(request.getCookies()!=null) for(Cookie c:request.getCookies()) if(SessaoEstacaoService.COOKIE.equals(c.getName())) try{return UUID.fromString(c.getValue());}catch(Exception ignored){}
        throw new SessaoEstacaoService.StationAccessException("SESSAO_ESTACAO_AUSENTE","A estação não possui uma sessão autorizada.");
    }
    public record LoginRequest(@NotBlank String login,@NotBlank String senha){}
    public record LoginResponse(String accessToken,UUID sessionId,java.time.OffsetDateTime expiresAt){}
}

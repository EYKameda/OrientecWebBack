package br.com.orientec.backend.adapter.in.web.estacao;

import br.com.orientec.backend.application.service.sessao.SessaoEstacaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stations")
public class EstacaoController {
    private final SessaoEstacaoService service;
    private final boolean secureCookie;
    public EstacaoController(SessaoEstacaoService service, @Value("${orientec.security.secure-cookie:false}") boolean secureCookie){this.service=service;this.secureCookie=secureCookie;}

    @PostMapping("/handshake")
    public HandshakeResponse handshake(@Valid @RequestBody HandshakeRequest r, HttpServletRequest request) {
        String observado = request.getRemoteAddr();
        var launch=service.handshake(r.sedeCodigo(),r.nomeMaquina(),r.ipLocal(),observado);
        return new HandshakeResponse(launch.launchCode(),launch.expiresAt());
    }

    @PostMapping("/exchange")
    public void exchange(@Valid @RequestBody ExchangeRequest r, HttpServletResponse response) {
        UUID sessionId=service.exchange(r.code());
        ResponseCookie cookie=ResponseCookie.from(SessaoEstacaoService.COOKIE,sessionId.toString())
                .httpOnly(true).secure(secureCookie).sameSite("Lax").path("/").maxAge(Duration.ofHours(12)).build();
        response.addHeader("Set-Cookie",cookie.toString());
    }

    @GetMapping("/session")
    public SessionResponse session(HttpServletRequest request) {
        UUID id=cookieId(request); service.validar(id); return new SessionResponse(true);
    }

    @PostMapping("/heartbeat")
    public void heartbeat(HttpServletRequest request){service.heartbeat(cookieId(request));}

    private UUID cookieId(HttpServletRequest request){
        if(request.getCookies()!=null) for(Cookie c:request.getCookies()) if(SessaoEstacaoService.COOKIE.equals(c.getName())) try{return UUID.fromString(c.getValue());}catch(Exception ignored){}
        throw new SessaoEstacaoService.StationAccessException("SESSAO_ESTACAO_AUSENTE","A estação não possui uma sessão autorizada.");
    }

    public record HandshakeRequest(@NotNull Long sedeCodigo,@NotBlank String nomeMaquina,String ipLocal){}
    public record HandshakeResponse(String launchCode, java.time.OffsetDateTime expiresAt){}
    public record ExchangeRequest(@NotBlank String code){}
    public record SessionResponse(boolean authorized){}
}

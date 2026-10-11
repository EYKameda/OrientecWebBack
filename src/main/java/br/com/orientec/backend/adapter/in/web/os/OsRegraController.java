package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.dto.os.*;
import br.com.orientec.backend.application.service.os.OsRegraEngineService;
import br.com.orientec.backend.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/os")
public class OsRegraController {
    private final OsRegraEngineService service;
    public OsRegraController(OsRegraEngineService service){this.service=service;}

    @GetMapping("/{osId}/acoes")
    @PreAuthorize("hasAuthority('PERM_OS_CONSULTAR')")
    public List<OsAcaoResponse> acoes(@PathVariable Long osId, Authentication authentication){
        var p=(JwtService.JwtPrincipal)authentication.getPrincipal();
        return service.acoes(osId,p.usuarioCodigo());
    }

    @PostMapping("/{osId}/transicoes/{destinoId}")
    @PreAuthorize("hasAuthority('PERM_OS_CONTROLAR') or hasAuthority('PERM_OS_RETROCEDER') or hasAuthority('PERM_OS_EXECUTAR') or hasAuthority('PERM_OS_APROVAR')")
    public void executar(@PathVariable Long osId,@PathVariable Short destinoId,@Valid @RequestBody OsTransicaoRequest request,Authentication authentication,@RequestHeader("Idempotency-Key") String idempotencyKey){
        var p=(JwtService.JwtPrincipal)authentication.getPrincipal();
        service.executar(osId,destinoId,p.usuarioCodigo(),request.autorizadorCodigo(),request.senhaAutorizador(),request.justificativa(),idempotencyKey);
    }
}

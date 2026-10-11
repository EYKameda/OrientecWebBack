package br.com.orientec.backend.adapter.in.web.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.adapter.out.persistence.repository.os.*;
import br.com.orientec.backend.application.service.os.*;

import br.com.orientec.backend.adapter.in.web.dto.os.RetrocessoRequest;
import br.com.orientec.backend.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/v1/os")
public class RetrocessoController {
    private final RetrocessoService service;
    public RetrocessoController(RetrocessoService service){this.service=service;}

    @PostMapping("/{osId}/retrocesso")
    public void retroceder(@PathVariable Long osId, @Valid @RequestBody RetrocessoRequest request,
                           Authentication authentication,
                           @RequestHeader("Idempotency-Key") String idempotencyKey) {
        var p=(JwtService.JwtPrincipal) authentication.getPrincipal();
        service.retroceder(osId, request.destinoId(), p.usuarioCodigo(),
                           request.autorizadorCodigo(), request.senhaAutorizador(),
                           request.justificativa(), idempotencyKey);
    }
}

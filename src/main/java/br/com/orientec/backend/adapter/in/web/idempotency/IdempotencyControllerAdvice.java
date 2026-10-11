package br.com.orientec.backend.adapter.in.web.idempotency;
import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.*;
import br.com.orientec.backend.adapter.out.persistence.repository.idempotency.*;
import br.com.orientec.backend.application.service.idempotency.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * Endpoint administrativo/teste para verificar a infraestrutura de idempotência.
 * Os endpoints de negócio devem chamar IdempotencyService.begin(...) e complete(...) dentro
 * do caso de uso transacional e completar o registro somente após o commit lógico.
 */
@RestController
public class IdempotencyControllerAdvice {

    @PostMapping("/api/v1/idempotency/test")
    public ResponseEntity<Map<String, String>> test(
            @RequestHeader("Idempotency-Key") String key) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of(
                        "status", "NOT_IMPLEMENTED",
                        "message", "Use IdempotencyService nos comandos de negócio."
                ));
    }
}

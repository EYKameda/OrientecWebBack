package br.com.orientec.backend.adapter.out.persistence.entity.idempotency;
import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.*;
import br.com.orientec.backend.adapter.out.persistence.repository.idempotency.*;
import br.com.orientec.backend.application.service.idempotency.*;

public record IdempotencyResponse(
        boolean replay,
        int status,
        String body
) {}

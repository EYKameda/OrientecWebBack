package br.com.orientec.backend.application.service.idempotency;
import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.service.idempotency.*;
import br.com.orientec.backend.application.port.out.IdempotencyPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

@Service
public class IdempotencyService {
    private final IdempotencyPersistencePort repository;

    public IdempotencyService(IdempotencyPersistencePort repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean begin(String key, String fingerprint) {
        if (key == null || key.isBlank())
            throw new IllegalArgumentException("Idempotency-Key é obrigatória.");

        String hash = sha256(fingerprint == null ? "" : fingerprint);
        var existing = repository.findByKeyForUpdate(key);

        if (existing.isPresent()) {
            if (!existing.get().getRequestHash().equals(hash))
                throw new IllegalArgumentException(
                    "A mesma Idempotency-Key foi reutilizada para uma requisição diferente.");
            if ("COMPLETED".equals(existing.get().getStatus()))
                return false;
            return true;
        }

        repository.saveAndFlush(new IdempotencyRecord(key, hash));
        return true;
    }

    @Transactional
    public void complete(String key, String fingerprint, int status, String body) {
        var record = repository.findByKeyForUpdate(key)
            .orElseThrow(() -> new IllegalStateException("Registro de idempotência não encontrado."));
        if (!record.getRequestHash().equals(sha256(fingerprint == null ? "" : fingerprint)))
            throw new IllegalArgumentException("Fingerprint de idempotência inconsistente.");
        record.complete(status, body);
    }

    private String sha256(String value) {
        try {
            return HexFormat.of().formatHex(
                MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível calcular hash de idempotência.", e);
        }
    }
}

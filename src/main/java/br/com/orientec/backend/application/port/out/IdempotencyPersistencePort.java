package br.com.orientec.backend.application.port.out;

import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.IdempotencyRecord;
import java.util.Optional;

public interface IdempotencyPersistencePort extends CrudPersistencePort<IdempotencyRecord, Long> {
    Optional<IdempotencyRecord> findByKeyForUpdate(String key);
    IdempotencyRecord saveAndFlush(IdempotencyRecord entity);
}

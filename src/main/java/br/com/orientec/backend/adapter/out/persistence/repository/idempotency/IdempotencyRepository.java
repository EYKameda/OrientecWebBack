package br.com.orientec.backend.adapter.out.persistence.repository.idempotency;
import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.IdempotencyPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.adapter.out.persistence.repository.idempotency.*;
import br.com.orientec.backend.application.service.idempotency.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<IdempotencyRecord, Long>, IdempotencyPersistencePort {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from IdempotencyRecord i where i.key = :key")
    Optional<IdempotencyRecord> findByKeyForUpdate(String key);
    Page<IdempotencyRecord> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<IdempotencyRecord> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

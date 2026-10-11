package br.com.orientec.backend.adapter.out.persistence.repository.sede;

import br.com.orientec.backend.adapter.out.persistence.entity.sede.Sede;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.application.port.out.sede.SedePersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SedeRepository extends JpaRepository<Sede, Long>, SedePersistencePort {
    Page<Sede> findByCodigoIsNotNull(Pageable pageable);
    @Override
    default PageResult<Sede> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByCodigoIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

package br.com.orientec.backend.adapter.out.persistence.repository.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredenciadoRepository extends JpaRepository<Credenciado,Integer>, CrudPersistencePort<Credenciado, Integer> {    Page<Credenciado> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<Credenciado> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

package br.com.orientec.backend.adapter.out.persistence.repository.usuario;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.PermissaoPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao,Long>, PermissaoPersistencePort{    Page<Permissao> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<Permissao> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

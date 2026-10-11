package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.application.port.out.os.RegraTransicaoPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.adapter.out.persistence.repository.os.*;
import br.com.orientec.backend.application.service.os.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.Optional;

public interface RegraTransicaoRepository extends JpaRepository<RegraTransicaoOs,Long>, RegraTransicaoPersistencePort {
    Optional<RegraTransicaoOs> findByOrigemIdAndDestinoIdAndAtivaTrue(Short origemId, Short destinoId);
    java.util.List<RegraTransicaoOs> findByOrigemIdAndAtivaTrue(Short origemId);
    Page<RegraTransicaoOs> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<RegraTransicaoOs> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

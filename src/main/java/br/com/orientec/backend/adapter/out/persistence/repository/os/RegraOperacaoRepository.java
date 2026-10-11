package br.com.orientec.backend.adapter.out.persistence.repository.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.application.port.out.os.RegraOperacaoPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegraOperacaoRepository extends JpaRepository<RegraOperacaoOs,Long>, RegraOperacaoPersistencePort {
    Optional<RegraOperacaoOs> findByCodigoAndAtivoTrue(String codigo);
    Page<RegraOperacaoOs> findByIdIsNotNull(Pageable pageable);
    @Override default PageResult<RegraOperacaoOs> findAll(PageQuery query){
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

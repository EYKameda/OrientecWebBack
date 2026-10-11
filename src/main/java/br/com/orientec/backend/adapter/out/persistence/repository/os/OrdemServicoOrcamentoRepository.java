package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrdemServicoOrcamentoRepository extends JpaRepository<OrdemServicoOrcamento,Long>, CrudPersistencePort<OrdemServicoOrcamento, Long>{List<OrdemServicoOrcamento> findByOrdemServicoId(Long osId);    Page<OrdemServicoOrcamento> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<OrdemServicoOrcamento> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

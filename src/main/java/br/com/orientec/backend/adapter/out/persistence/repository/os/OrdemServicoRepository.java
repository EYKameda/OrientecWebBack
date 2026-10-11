package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.application.port.out.os.OrdemServicoPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import jakarta.persistence.LockModeType;
import java.util.*;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Long>, OrdemServicoPersistencePort{ @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select o from OrdemServico o join fetch o.situacao where o.id=:id") Optional<OrdemServico> findByIdForUpdate(Long id); List<OrdemServico> findBySituacao_Id(Short id);
    Page<OrdemServico> findBySituacao_Id(Short id, Pageable pageable);
    default List<OrdemServico> findBySituacaoId(Short id){ return findBySituacao_Id(id); }
    List<OrdemServico> findByClienteId(Integer id);
    Page<OrdemServico> findByClienteId(Integer id, Pageable pageable);     Page<OrdemServico> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<OrdemServico> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

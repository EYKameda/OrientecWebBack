package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.application.port.out.os.OrdemServicoHistoricoPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.adapter.out.persistence.repository.os.*;
import br.com.orientec.backend.application.service.os.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoHistoricoRepository extends JpaRepository<OrdemServicoHistorico,Long>, OrdemServicoHistoricoPersistencePort {
    java.util.List<OrdemServicoHistorico> findByOrdemServicoIdOrderByDataHoraDesc(Long ordemServicoId);    Page<OrdemServicoHistorico> findByIdIsNotNull(Pageable pageable);
    @Override
    default PageResult<OrdemServicoHistorico> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByIdIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

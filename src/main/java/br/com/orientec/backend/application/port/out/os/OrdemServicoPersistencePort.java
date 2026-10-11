package br.com.orientec.backend.application.port.out.os;

import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoPersistencePort extends CrudPersistencePort<OrdemServico, Long> {
    Optional<OrdemServico> findByIdForUpdate(Long id);
    List<OrdemServico> findBySituacaoId(Short id);
    List<OrdemServico> findByClienteId(Integer id);

    Page<OrdemServico> findBySituacao_Id(Short statusId, Pageable pageable);

    Page<OrdemServico> findByClienteId(Integer id, Pageable pageable);

}

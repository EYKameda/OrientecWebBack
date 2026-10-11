package br.com.orientec.backend.application.port.out.os;

import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoHistorico;
import java.util.List;

public interface OrdemServicoHistoricoPersistencePort extends CrudPersistencePort<OrdemServicoHistorico, Long> {
    List<OrdemServicoHistorico> findByOrdemServicoIdOrderByDataHoraDesc(Long ordemServicoId);
}

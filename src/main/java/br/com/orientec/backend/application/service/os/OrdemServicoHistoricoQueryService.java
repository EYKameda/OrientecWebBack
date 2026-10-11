package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoHistorico;
import br.com.orientec.backend.application.port.out.os.OrdemServicoHistoricoPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrdemServicoHistoricoQueryService {
    private final OrdemServicoHistoricoPersistencePort repository;
    public OrdemServicoHistoricoQueryService(OrdemServicoHistoricoPersistencePort repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public List<OrdemServicoHistorico> listar(Long osId) {
        return repository.findByOrdemServicoIdOrderByDataHoraDesc(osId);
    }
}

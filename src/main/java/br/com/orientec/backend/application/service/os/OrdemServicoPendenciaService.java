package br.com.orientec.backend.application.service.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoPendenciaService extends CrudApplicationService<OrdemServicoPendencia,Long>{public OrdemServicoPendenciaService(CrudPersistencePort<OrdemServicoPendencia, Long> r){super(r);}}

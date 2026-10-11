package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class OrigemOsService extends CrudApplicationService<OrigemOs,Short> { public OrigemOsService(CrudPersistencePort<OrigemOs, Short> r){super(r);} }

package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class DefeitoService extends CrudApplicationService<Defeito,Short> { public DefeitoService(CrudPersistencePort<Defeito, Short> r){super(r);} }

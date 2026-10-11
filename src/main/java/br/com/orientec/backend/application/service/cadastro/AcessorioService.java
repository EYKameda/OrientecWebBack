package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class AcessorioService extends CrudApplicationService<Acessorio,Short> { public AcessorioService(CrudPersistencePort<Acessorio, Short> r){super(r);} }

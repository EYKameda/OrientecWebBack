package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CredenciadoService extends CrudApplicationService<Credenciado,Integer> { public CredenciadoService(CrudPersistencePort<Credenciado, Integer> r){super(r);} }

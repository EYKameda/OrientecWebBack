package br.com.orientec.backend.application.service.usuario;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService extends CrudApplicationService<Permissao,Long>{public PermissaoService(CrudPersistencePort<Permissao, Long> r){super(r);}}

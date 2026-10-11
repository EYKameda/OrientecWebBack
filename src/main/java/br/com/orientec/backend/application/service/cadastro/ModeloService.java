package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ModeloService extends CrudApplicationService<Modelo,Integer>{public ModeloService(CrudPersistencePort<Modelo, Integer> r){super(r);}}

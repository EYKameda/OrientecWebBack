package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class TipoPendenciaService extends CrudApplicationService<TipoPendencia,Short> { public TipoPendenciaService(CrudPersistencePort<TipoPendencia, Short> r){super(r);} }

package br.com.orientec.backend.application.service.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService extends CrudApplicationService<Fornecedor,Integer> { public FornecedorService(CrudPersistencePort<Fornecedor, Integer> r){super(r);} }

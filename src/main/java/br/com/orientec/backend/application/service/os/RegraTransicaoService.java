package br.com.orientec.backend.application.service.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraTransicaoOs;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;
@Service
public class RegraTransicaoService extends CrudApplicationService<RegraTransicaoOs,Long>{
 public RegraTransicaoService(CrudPersistencePort<RegraTransicaoOs,Long> r){super(r);}
 @Override public void excluir(Long id){throw new IllegalStateException("Regra de transição não pode ser excluída. Inative-a.");}
}

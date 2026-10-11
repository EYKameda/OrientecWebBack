package br.com.orientec.backend.application.port.out.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import java.util.Optional;
public interface RegraOperacaoPersistencePort extends CrudPersistencePort<RegraOperacaoOs,Long>{
 Optional<RegraOperacaoOs> findByCodigoAndAtivoTrue(String codigo);
}

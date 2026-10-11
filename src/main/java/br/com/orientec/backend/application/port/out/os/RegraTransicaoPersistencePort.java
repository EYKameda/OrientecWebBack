package br.com.orientec.backend.application.port.out.os;

import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraTransicaoOs;
import java.util.Optional;

public interface RegraTransicaoPersistencePort extends CrudPersistencePort<RegraTransicaoOs, Long> {
    Optional<RegraTransicaoOs> findByOrigemIdAndDestinoIdAndAtivaTrue(Short origemId, Short destinoId);
    java.util.List<RegraTransicaoOs> findByOrigemIdAndAtivaTrue(Short origemId);
}

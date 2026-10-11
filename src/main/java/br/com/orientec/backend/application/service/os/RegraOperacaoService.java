package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class RegraOperacaoService extends CrudApplicationService<RegraOperacaoOs,Long> {
    public RegraOperacaoService(CrudPersistencePort<RegraOperacaoOs,Long> persistence){ super(persistence); }
    @Override public void excluir(Long id){ throw new IllegalStateException("Regra de operação não pode ser excluída. Inative-a."); }
}

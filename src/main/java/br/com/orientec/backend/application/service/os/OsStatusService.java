package br.com.orientec.backend.application.service.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class OsStatusService extends CrudApplicationService<OsStatus,Short>{public OsStatusService(CrudPersistencePort<OsStatus, Short> r){super(r);} @Override public void excluir(Short id){ throw new IllegalStateException("Situação da OS não pode ser excluída. Inative-a para preservar o histórico."); }}

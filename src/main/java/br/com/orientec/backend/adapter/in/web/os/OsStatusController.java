package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.OsStatusRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OsStatusResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.OsStatusMapper;
import br.com.orientec.backend.application.service.os.OsStatusService;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OsStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/os-status")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class OsStatusController extends CrudController<
        OsStatusRequest, OsStatusResponse, OsStatus, Short, OsStatusService> {

    public OsStatusController(OsStatusService service, OsStatusMapper mapper) {
        super(service, mapper);
    }

    @Override protected String resourcePath() { return "os-status"; }
    @Override protected Object idOf(OsStatus entity) { return entity.getId(); }
}

package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.OrigemOsRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.OrigemOsResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.OrigemOsMapper;
import br.com.orientec.backend.application.service.cadastro.OrigemOsService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.OrigemOs;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/origem_os")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class OrigemOsController extends CrudController<
        OrigemOsRequest, OrigemOsResponse, OrigemOs, Short, OrigemOsService> {

    public OrigemOsController(OrigemOsService service, OrigemOsMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "origem_os";
    }

    @Override
    protected Object idOf(OrigemOs entity) {
        return entity.getId();
    }
}

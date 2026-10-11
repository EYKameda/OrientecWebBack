package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPecaRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPecaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.OrdemServicoPecaMapper;
import br.com.orientec.backend.application.service.os.OrdemServicoPecaService;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoPeca;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordem_servico_peca")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class OrdemServicoPecaController extends CrudController<
        OrdemServicoPecaRequest, OrdemServicoPecaResponse, OrdemServicoPeca, Long, OrdemServicoPecaService> {

    public OrdemServicoPecaController(OrdemServicoPecaService service, OrdemServicoPecaMapper mapper) {
        super(service, mapper);
    }

    @Override protected String resourcePath() { return "ordem_servico_peca"; }
    @Override protected Object idOf(OrdemServicoPeca entity) { return entity.getId(); }
}

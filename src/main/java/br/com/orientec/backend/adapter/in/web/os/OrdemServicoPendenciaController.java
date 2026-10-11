package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPendenciaRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPendenciaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.OrdemServicoPendenciaMapper;
import br.com.orientec.backend.application.service.os.OrdemServicoPendenciaService;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoPendencia;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordem_servico_pendencia")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class OrdemServicoPendenciaController extends CrudController<
        OrdemServicoPendenciaRequest, OrdemServicoPendenciaResponse, OrdemServicoPendencia, Long, OrdemServicoPendenciaService> {

    public OrdemServicoPendenciaController(OrdemServicoPendenciaService service, OrdemServicoPendenciaMapper mapper) {
        super(service, mapper);
    }

    @Override protected String resourcePath() { return "ordem_servico_pendencia"; }
    @Override protected Object idOf(OrdemServicoPendencia entity) { return entity.getId(); }
}

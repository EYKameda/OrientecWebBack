package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoPendenciaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoPendenciaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.TipoPendenciaMapper;
import br.com.orientec.backend.application.service.cadastro.TipoPendenciaService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoPendencia;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipo_pendencia")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class TipoPendenciaController extends CrudController<
        TipoPendenciaRequest, TipoPendenciaResponse, TipoPendencia, Short, TipoPendenciaService> {

    public TipoPendenciaController(TipoPendenciaService service, TipoPendenciaMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "tipo_pendencia";
    }

    @Override
    protected Object idOf(TipoPendencia entity) {
        return entity.getId();
    }
}

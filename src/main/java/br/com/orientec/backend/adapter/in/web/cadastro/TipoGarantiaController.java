package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoGarantiaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoGarantiaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.TipoGarantiaMapper;
import br.com.orientec.backend.application.service.cadastro.TipoGarantiaService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoGarantia;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipo_garantia")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class TipoGarantiaController extends CrudController<
        TipoGarantiaRequest, TipoGarantiaResponse, TipoGarantia, Short, TipoGarantiaService> {

    public TipoGarantiaController(TipoGarantiaService service, TipoGarantiaMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "tipo_garantia";
    }

    @Override
    protected Object idOf(TipoGarantia entity) {
        return entity.getId();
    }
}

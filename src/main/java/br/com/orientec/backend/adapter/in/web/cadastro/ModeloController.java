package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ModeloRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ModeloResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.ModeloMapper;
import br.com.orientec.backend.application.service.cadastro.ModeloService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Modelo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modelo")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class ModeloController extends CrudController<
        ModeloRequest, ModeloResponse, Modelo, Integer, ModeloService> {

    public ModeloController(ModeloService service, ModeloMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "modelo";
    }

    @Override
    protected Object idOf(Modelo entity) {
        return entity.getId();
    }
}

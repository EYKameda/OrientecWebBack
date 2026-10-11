package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.MarcaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.MarcaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.MarcaMapper;
import br.com.orientec.backend.application.service.cadastro.MarcaService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Marca;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marca")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class MarcaController extends CrudController<
        MarcaRequest, MarcaResponse, Marca, Integer, MarcaService> {

    public MarcaController(MarcaService service, MarcaMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "marca";
    }

    @Override
    protected Object idOf(Marca entity) {
        return entity.getId();
    }
}

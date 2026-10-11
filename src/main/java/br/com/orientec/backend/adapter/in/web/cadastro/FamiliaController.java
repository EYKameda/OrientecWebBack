package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FamiliaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FamiliaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.FamiliaMapper;
import br.com.orientec.backend.application.service.cadastro.FamiliaService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Familia;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/familia")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class FamiliaController extends CrudController<
        FamiliaRequest, FamiliaResponse, Familia, Integer, FamiliaService> {

    public FamiliaController(FamiliaService service, FamiliaMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "familia";
    }

    @Override
    protected Object idOf(Familia entity) {
        return entity.getId();
    }
}

package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.LinhaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.LinhaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.LinhaMapper;
import br.com.orientec.backend.application.service.cadastro.LinhaService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Linha;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/linha")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class LinhaController extends CrudController<
        LinhaRequest, LinhaResponse, Linha, Integer, LinhaService> {

    public LinhaController(LinhaService service, LinhaMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "linha";
    }

    @Override
    protected Object idOf(Linha entity) {
        return entity.getId();
    }
}

package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.DefeitoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.DefeitoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.DefeitoMapper;
import br.com.orientec.backend.application.service.cadastro.DefeitoService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Defeito;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defeito")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class DefeitoController extends CrudController<
        DefeitoRequest, DefeitoResponse, Defeito, Short, DefeitoService> {

    public DefeitoController(DefeitoService service, DefeitoMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "defeito";
    }

    @Override
    protected Object idOf(Defeito entity) {
        return entity.getId();
    }
}

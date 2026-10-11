package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.AcessorioRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.AcessorioResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.AcessorioMapper;
import br.com.orientec.backend.application.service.cadastro.AcessorioService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Acessorio;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/acessorio")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class AcessorioController extends CrudController<
        AcessorioRequest, AcessorioResponse, Acessorio, Short, AcessorioService> {

    public AcessorioController(AcessorioService service, AcessorioMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "acessorio";
    }

    @Override
    protected Object idOf(Acessorio entity) {
        return entity.getId();
    }
}

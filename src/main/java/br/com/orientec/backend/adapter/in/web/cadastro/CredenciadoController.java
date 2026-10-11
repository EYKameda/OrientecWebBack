package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.CredenciadoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.CredenciadoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.CredenciadoMapper;
import br.com.orientec.backend.application.service.cadastro.CredenciadoService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Credenciado;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credenciado")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class CredenciadoController extends CrudController<
        CredenciadoRequest, CredenciadoResponse, Credenciado, Integer, CredenciadoService> {

    public CredenciadoController(CredenciadoService service, CredenciadoMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "credenciado";
    }

    @Override
    protected Object idOf(Credenciado entity) {
        return entity.getId();
    }
}

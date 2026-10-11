package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ClienteRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ClienteResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.ClienteMapper;
import br.com.orientec.backend.application.service.cadastro.ClienteService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Cliente;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cliente")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class ClienteController extends CrudController<
        ClienteRequest, ClienteResponse, Cliente, Integer, ClienteService> {

    public ClienteController(ClienteService service, ClienteMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "cliente";
    }

    @Override
    protected Object idOf(Cliente entity) {
        return entity.getId();
    }
}

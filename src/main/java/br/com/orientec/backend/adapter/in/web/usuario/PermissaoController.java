package br.com.orientec.backend.adapter.in.web.usuario;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.usuario.PermissaoRequest;
import br.com.orientec.backend.adapter.in.web.dto.usuario.PermissaoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.usuario.PermissaoMapper;
import br.com.orientec.backend.application.service.usuario.PermissaoService;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Permissao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/permissao")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class PermissaoController extends CrudController<
        PermissaoRequest, PermissaoResponse, Permissao, Long, PermissaoService> {

    public PermissaoController(PermissaoService service, PermissaoMapper mapper) {
        super(service, mapper);
    }

    @Override protected String resourcePath() { return "permissao"; }
    @Override protected Object idOf(Permissao entity) { return entity.getId(); }
}

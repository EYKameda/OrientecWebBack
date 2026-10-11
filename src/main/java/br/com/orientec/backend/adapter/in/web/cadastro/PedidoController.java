package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.PedidoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.PedidoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.PedidoMapper;
import br.com.orientec.backend.application.service.cadastro.PedidoService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Pedido;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedido")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class PedidoController extends CrudController<
        PedidoRequest, PedidoResponse, Pedido, Integer, PedidoService> {

    public PedidoController(PedidoService service, PedidoMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "pedido";
    }

    @Override
    protected Object idOf(Pedido entity) {
        return entity.getId();
    }
}

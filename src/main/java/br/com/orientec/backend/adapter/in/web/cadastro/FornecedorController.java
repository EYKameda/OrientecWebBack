package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FornecedorRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FornecedorResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.FornecedorMapper;
import br.com.orientec.backend.application.service.cadastro.FornecedorService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Fornecedor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fornecedor")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class FornecedorController extends CrudController<
        FornecedorRequest, FornecedorResponse, Fornecedor, Integer, FornecedorService> {

    public FornecedorController(FornecedorService service, FornecedorMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "fornecedor";
    }

    @Override
    protected Object idOf(Fornecedor entity) {
        return entity.getId();
    }
}

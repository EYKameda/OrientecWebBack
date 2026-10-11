package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoHistoricoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.OrdemServicoHistoricoMapper;
import br.com.orientec.backend.application.service.os.OrdemServicoHistoricoQueryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/os/{osId}/historico")
@PreAuthorize("hasAuthority('PERM_OS_CONSULTAR')")
public class OrdemServicoHistoricoController {
    private final OrdemServicoHistoricoQueryService service;
    private final OrdemServicoHistoricoMapper mapper;

    public OrdemServicoHistoricoController(OrdemServicoHistoricoQueryService service,
                                            OrdemServicoHistoricoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<OrdemServicoHistoricoResponse> listar(@PathVariable Long osId) {
        return service.listar(osId).stream().map(mapper::toResponse).toList();
    }
}

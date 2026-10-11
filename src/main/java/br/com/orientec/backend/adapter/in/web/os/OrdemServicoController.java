package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.OrdemServicoMapper;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.application.service.os.OrdemServicoService;
import br.com.orientec.backend.security.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/os")
public class OrdemServicoController {
    private final OrdemServicoService service;
    private final OrdemServicoMapper mapper;

    public OrdemServicoController(OrdemServicoService service, OrdemServicoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PERM_OS_CONSULTAR')")
    public PageResult<OrdemServicoResponse> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) Short statusId,
            @RequestParam(required = false) Integer clienteId) {
        return service.listar(new PageQuery(page, size, sort), statusId, clienteId).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_OS_CONSULTAR')")
    public OrdemServicoResponse buscar(@PathVariable Long id) {
        return mapper.toResponse(service.buscar(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PERM_OS_ABRIR')")
    public OrdemServicoResponse abrir(@RequestBody OrdemServicoRequest request, Authentication authentication) {
        var principal = (JwtService.JwtPrincipal) authentication.getPrincipal();
        return mapper.toResponse(service.abrir(mapper.toEntity(request), principal.usuarioCodigo()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
    public OrdemServicoResponse atualizar(@PathVariable Long id, @RequestBody OrdemServicoRequest request) {
        return mapper.toResponse(service.atualizar(id, mapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}

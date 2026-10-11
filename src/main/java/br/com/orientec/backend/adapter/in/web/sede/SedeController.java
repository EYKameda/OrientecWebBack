package br.com.orientec.backend.adapter.in.web.sede;

import br.com.orientec.backend.adapter.in.web.dto.sede.SedeRequest;
import br.com.orientec.backend.adapter.in.web.dto.sede.SedeResponse;
import br.com.orientec.backend.adapter.in.web.mapper.sede.SedeMapper;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.application.service.sede.SedeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sedes")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
@Validated
public class SedeController {
    private final SedeService service;
    private final SedeMapper mapper;
    public SedeController(SedeService service, SedeMapper mapper) { this.service = service; this.mapper = mapper; }

    @GetMapping
    public PageResult<SedeResponse> listar(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size,
                                           @RequestParam(defaultValue = "codigo") String sort) {
        return service.listar(new PageQuery(page, size, sort)).map(mapper::toResponse);
    }

    @GetMapping("/{id}") public SedeResponse buscar(@PathVariable Long id) { return mapper.toResponse(service.buscar(id)); }
    @PostMapping public SedeResponse criar(@Valid @RequestBody SedeRequest r) { return mapper.toResponse(service.criar(mapper.toEntity(r))); }
    @PutMapping("/{id}") public SedeResponse atualizar(@PathVariable Long id, @Valid @RequestBody SedeRequest r) { return mapper.toResponse(service.atualizar(id, mapper.toEntity(r))); }
    @DeleteMapping("/{id}") public void excluir(@PathVariable Long id) { service.inativar(id); }
}

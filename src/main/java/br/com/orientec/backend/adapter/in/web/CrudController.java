package br.com.orientec.backend.adapter.in.web;

import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

public abstract class CrudController<REQ, RES, E, ID, S extends CrudApplicationService<E, ID>>
        extends BaseController {

    protected final S service;
    protected final ApiMapper<REQ, RES, E> mapper;

    protected CrudController(S service, ApiMapper<REQ, RES, E> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public PageResult<RES> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {
        return service.listar(new PageQuery(page, size, sort)).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public RES buscar(@PathVariable ID id) {
        return mapper.toResponse(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<RES> criar(@RequestBody REQ body) {
        E saved = service.criar(mapper.toEntity(body));
        return ResponseEntity.created(URI.create("/" + resourcePath() + "/" + idOf(saved)))
                .body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public RES atualizar(@PathVariable ID id, @RequestBody REQ body) {
        return mapper.toResponse(service.atualizar(id, e -> mapper.updateEntity(body, e)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable ID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    protected abstract String resourcePath();
    protected abstract Object idOf(E entity);
}

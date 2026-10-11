package br.com.orientec.backend.application.service;

import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import org.springframework.transaction.annotation.Transactional;

public abstract class CrudApplicationService<E, ID> {
    protected final CrudPersistencePort<E, ID> persistence;

    protected CrudApplicationService(CrudPersistencePort<E, ID> persistence) {
        this.persistence = persistence;
    }

    @Transactional(readOnly = true)
    public PageResult<E> listar(PageQuery query) {
        return persistence.findAll(query);
    }

    @Transactional(readOnly = true)
    public E buscar(ID id) {
        return persistence.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro não encontrado: " + id));
    }

    @Transactional
    public E criar(E entity) {
        return persistence.save(entity);
    }

    @Transactional
    public E atualizar(ID id, java.util.function.Consumer<E> updater) {
        E entity = buscar(id);
        updater.accept(entity);
        return persistence.save(entity);
    }

    @Transactional
    public void excluir(ID id) {
        if (!persistence.existsById(id)) {
            throw new IllegalArgumentException("Registro não encontrado: " + id);
        }
        persistence.deleteById(id);
    }
}

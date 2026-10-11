package br.com.orientec.backend.application.port.out;

import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import java.util.Optional;

public interface CrudPersistencePort<E, ID> {
    PageResult<E> findAll(PageQuery query);
    Optional<E> findById(ID id);
    E save(E entity);
    boolean existsById(ID id);
    void deleteById(ID id);
}

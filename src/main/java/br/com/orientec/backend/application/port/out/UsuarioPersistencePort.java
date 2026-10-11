package br.com.orientec.backend.application.port.out;

import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import java.util.Optional;

public interface UsuarioPersistencePort extends CrudPersistencePort<Usuario, Long> {
    Optional<Usuario> findByLoginIgnoreCase(String login);
}

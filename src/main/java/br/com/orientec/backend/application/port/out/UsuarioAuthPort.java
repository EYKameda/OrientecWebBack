package br.com.orientec.backend.application.port.out;

import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario;
import java.util.Optional;

public interface UsuarioAuthPort {
    Optional<Usuario> findByLoginIgnoreCase(String login);
}

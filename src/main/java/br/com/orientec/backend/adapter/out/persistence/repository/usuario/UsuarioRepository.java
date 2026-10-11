package br.com.orientec.backend.adapter.out.persistence.repository.usuario;
import br.com.orientec.backend.application.port.out.UsuarioPersistencePort;
import br.com.orientec.backend.application.port.out.UsuarioAuthPort;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioPersistencePort, UsuarioAuthPort {
    Optional<Usuario> findByLoginIgnoreCase(String login);
    Page<Usuario> findByCodigoIsNotNull(Pageable pageable);
    @Override
    default PageResult<Usuario> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(findByCodigoIsNotNull(SpringDataPageAdapter.toPageable(query)));
    }
}

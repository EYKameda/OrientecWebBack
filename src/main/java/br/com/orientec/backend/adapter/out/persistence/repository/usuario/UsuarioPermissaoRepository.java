package br.com.orientec.backend.adapter.out.persistence.repository.usuario;

import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.UsuarioPermissao;
import br.com.orientec.backend.application.port.out.UsuarioPermissaoPersistencePort;
import br.com.orientec.backend.application.port.out.UsuarioPermissionPort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UsuarioPermissaoRepository
        extends JpaRepository<UsuarioPermissao, UsuarioPermissao.Id>,
        UsuarioPermissaoPersistencePort,
        UsuarioPermissionPort {

    @Query("""
            select p.codigo
            from UsuarioPermissao up
            join Permissao p on p.id = up.id.permissaoId
            where up.id.usuarioCodigo = :usuarioCodigo
              and p.ativo = true
            """)
    Set<String> findCodigosByUsuario(Long usuarioCodigo);

    @Modifying
    @Query("""
            delete from UsuarioPermissao up
            where up.id.usuarioCodigo = :usuarioCodigo
            """)
    @Override
    void deleteByUsuarioCodigo(@Param("usuarioCodigo") Long usuarioCodigo);

    Page<UsuarioPermissao> findByIdIsNotNull(Pageable pageable);

    @Override
    default PageResult<UsuarioPermissao> findAll(PageQuery query) {
        return SpringDataPageAdapter.toResult(
                findByIdIsNotNull(
                        SpringDataPageAdapter.toPageable(query)
                )
        );
    }
}
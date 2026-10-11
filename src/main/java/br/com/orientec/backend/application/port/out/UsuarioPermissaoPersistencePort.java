package br.com.orientec.backend.application.port.out;

import br.com.orientec.backend.adapter.out.persistence.entity.usuario.UsuarioPermissao;

import java.util.Set;

public interface UsuarioPermissaoPersistencePort
        extends CrudPersistencePort<UsuarioPermissao, UsuarioPermissao.Id> {

    Set<String> findCodigosByUsuario(Long usuarioCodigo);

    void deleteByUsuarioCodigo(Long usuarioCodigo);
}
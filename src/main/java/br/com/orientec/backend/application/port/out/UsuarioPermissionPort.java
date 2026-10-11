package br.com.orientec.backend.application.port.out;

import java.util.Set;

public interface UsuarioPermissionPort {
    Set<String> findCodigosByUsuario(Long usuarioCodigo);
}

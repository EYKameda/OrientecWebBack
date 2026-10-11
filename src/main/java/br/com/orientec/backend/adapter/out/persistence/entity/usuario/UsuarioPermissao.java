package br.com.orientec.backend.adapter.out.persistence.entity.usuario;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.adapter.out.persistence.repository.usuario.*;
import br.com.orientec.backend.application.service.usuario.*;

import jakarta.persistence.*;

@Entity
@Table(name="usuario_permissao")
public class UsuarioPermissao {
    @EmbeddedId
    private Id id;

    protected UsuarioPermissao() {}
    public UsuarioPermissao(Id id) { this.id=id; }

    public Id getId() {
        return this.id;
    }

    @Embeddable
    public static class Id implements java.io.Serializable {
        @Column(name="usuario_codigo") private Long usuarioCodigo;
        @Column(name="permissao_id") private Long permissaoId;

        public Id() {}
        public Id(Long usuarioCodigo, Long permissaoId) {
            this.usuarioCodigo = usuarioCodigo;
            this.permissaoId = permissaoId;
        }
        public Long getUsuarioCodigo() { return usuarioCodigo; }
        public Long getPermissaoId() { return permissaoId; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id other)) return false;
            return java.util.Objects.equals(usuarioCodigo, other.usuarioCodigo)
                && java.util.Objects.equals(permissaoId, other.permissaoId);
        }
        @Override public int hashCode() {
            return java.util.Objects.hash(usuarioCodigo, permissaoId);
        }
    }
}

package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="tipo_garantia") public class TipoGarantia {
 @Id private Short id;
 @Column(name="codigo") private String codigo; @Column(name="descricao") private String descricao; @Column(name="ativo") private boolean ativo;
 public Short getId(){return id;} public void setId(Short v){id=v;}
 public TipoGarantia(){}
 public String getCodigo(){return codigo;} public String getDescricao(){return descricao;} public boolean isAtivo(){return ativo;} public void setCodigo(String v){this.codigo=v;} public void setDescricao(String v){this.descricao=v;} public void setAtivo(boolean v){this.ativo=v;}
}

package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="defeito") public class Defeito {
 @Id private Short id;
 @Column(name="descricao") private String descricao; @Column(name="ativo") private boolean ativo;
 public Short getId(){return id;} public void setId(Short v){id=v;}
 public Defeito(){}
 public String getDescricao(){return descricao;} public boolean isAtivo(){return ativo;} public void setDescricao(String v){this.descricao=v;} public void setAtivo(boolean v){this.ativo=v;}
}

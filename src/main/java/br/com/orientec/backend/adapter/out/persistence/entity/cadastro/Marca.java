package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="marca") public class Marca {
 @Id private Integer id;
 @Column(name="descricao") private String descricao; @Column(name="ativo") private boolean ativo;
 public Integer getId(){return id;} public void setId(Integer v){id=v;}
 public Marca(){}
 public String getDescricao(){return descricao;} public boolean isAtivo(){return ativo;} public void setDescricao(String v){this.descricao=v;} public void setAtivo(boolean v){this.ativo=v;}
}

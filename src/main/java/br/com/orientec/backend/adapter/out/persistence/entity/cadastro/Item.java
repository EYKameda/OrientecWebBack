package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="item") public class Item {
 @Id private Integer id;
 @Column(name="descricao") private String descricao; @Column(name="aplicacao") private String aplicacao; @Column(name="codigo_barras") private String codigoBarras; @Column(name="familia_id") private Integer familiaId; @Column(name="ativo") private boolean ativo;
 public Integer getId(){return id;} public void setId(Integer v){id=v;}
 public Item(){}
 public String getDescricao(){return descricao;} public String getAplicacao(){return aplicacao;} public String getCodigoBarras(){return codigoBarras;} public Integer getFamiliaId(){return familiaId;} public boolean isAtivo(){return ativo;} public void setDescricao(String v){this.descricao=v;} public void setAplicacao(String v){this.aplicacao=v;} public void setCodigoBarras(String v){this.codigoBarras=v;} public void setFamiliaId(Integer v){this.familiaId=v;} public void setAtivo(boolean v){this.ativo=v;}
}

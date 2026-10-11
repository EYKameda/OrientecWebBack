package br.com.orientec.backend.adapter.out.persistence.entity.cadastro;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.*;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.*;
import br.com.orientec.backend.application.service.cadastro.*;
import jakarta.persistence.*;
@Entity @Table(name="modelo") public class Modelo { @Id private Integer id; @Column(name="marca_id",nullable=false) private Integer marcaId; @Column(name="linha_id") private Integer linhaId; @Column(nullable=false) private String descricao; private String complemento; @Column(nullable=false) private boolean ativo=true; public Modelo(){} public Integer getId(){return id;} public Integer getMarcaId(){return marcaId;} public Integer getLinhaId(){return linhaId;} public String getDescricao(){return descricao;} public String getComplemento(){return complemento;} public boolean isAtivo(){return ativo;} public void setId(Integer v){id=v;} public void setMarcaId(Integer v){marcaId=v;} public void setLinhaId(Integer v){linhaId=v;} public void setDescricao(String v){descricao=v;} public void setComplemento(String v){complemento=v;} public void setAtivo(boolean v){ativo=v;} }

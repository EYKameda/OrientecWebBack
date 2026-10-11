package br.com.orientec.backend.adapter.out.persistence.entity.usuario;
import br.com.orientec.backend.adapter.out.persistence.entity.usuario.*;
import br.com.orientec.backend.adapter.out.persistence.repository.usuario.*;
import br.com.orientec.backend.application.service.usuario.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="usuario")
public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long codigo;
 @Column(nullable=false) private String nome;
 @Column private String apelido;
 @Column(nullable=false,unique=true) private String login;
 @JsonIgnore @Column(name="senha_hash",nullable=false) private String senhaHash;
 @Column(name="nivel_codigo",nullable=false) private Short nivelCodigo;
 @Column(name="sede_codigo") private Long sedeCodigo;
 @Column(nullable=false) private boolean ativo=true;
 @Column(name="tempo_ocioso_minutos",nullable=false) private Integer tempoOciosoMinutos=30;
 @Column(name="tempo_maximo_sessao_minutos",nullable=false) private Integer tempoMaximoSessaoMinutos=480;
 @Column(name="modulo_os",nullable=false) private boolean moduloOs;
 @Column(name="modulo_cadastros",nullable=false) private boolean moduloCadastros;
 @Column(name="modulo_caixa",nullable=false) private boolean moduloCaixa;
 @Column(name="modulo_estoque",nullable=false) private boolean moduloEstoque;
 @Column(name="modulo_financeiro",nullable=false) private boolean moduloFinanceiro;
 @Column(name="modulo_gerencial",nullable=false) private boolean moduloGerencial;
 @Column(name="modulo_ponto",nullable=false) private boolean moduloPonto;
 @Column(name="modulo_tabelas",nullable=false) private boolean moduloTabelas;
 @Column(name="modulo_vendas",nullable=false) private boolean moduloVendas;
 protected Usuario(){}
 public static Usuario novo(){return new Usuario();}
 public Long getCodigo(){return codigo;} public Integer getTempoOciosoMinutos(){return tempoOciosoMinutos;} public Integer getTempoMaximoSessaoMinutos(){return tempoMaximoSessaoMinutos;} public String getNome(){return nome;} public String getApelido(){return apelido;} public String getLogin(){return login;} public String getSenhaHash(){return senhaHash;} public Short getNivelCodigo(){return nivelCodigo;} public Long getSedeCodigo(){return sedeCodigo;} public boolean isAtivo(){return ativo;}
 public boolean isModuloOs(){return moduloOs;} public boolean isModuloCadastros(){return moduloCadastros;} public boolean isModuloCaixa(){return moduloCaixa;} public boolean isModuloEstoque(){return moduloEstoque;} public boolean isModuloFinanceiro(){return moduloFinanceiro;} public boolean isModuloGerencial(){return moduloGerencial;} public boolean isModuloPonto(){return moduloPonto;} public boolean isModuloTabelas(){return moduloTabelas;} public boolean isModuloVendas(){return moduloVendas;}
 public void setTempoOciosoMinutos(Integer v){tempoOciosoMinutos=v;} public void setTempoMaximoSessaoMinutos(Integer v){tempoMaximoSessaoMinutos=v;} public void setNome(String v){nome=v;} public void setApelido(String v){apelido=v;} public void setLogin(String v){login=v;} public void setSenhaHash(String v){senhaHash=v;} public void setNivelCodigo(Short v){nivelCodigo=v;} public void setSedeCodigo(Long v){sedeCodigo=v;} public void setAtivo(boolean v){ativo=v;} public void setModuloOs(boolean v){moduloOs=v;} public void setModuloCadastros(boolean v){moduloCadastros=v;} public void setModuloCaixa(boolean v){moduloCaixa=v;} public void setModuloEstoque(boolean v){moduloEstoque=v;} public void setModuloFinanceiro(boolean v){moduloFinanceiro=v;} public void setModuloGerencial(boolean v){moduloGerencial=v;} public void setModuloPonto(boolean v){moduloPonto=v;} public void setModuloTabelas(boolean v){moduloTabelas=v;} public void setModuloVendas(boolean v){moduloVendas=v;}
}

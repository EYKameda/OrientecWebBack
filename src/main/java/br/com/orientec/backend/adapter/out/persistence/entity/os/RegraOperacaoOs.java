package br.com.orientec.backend.adapter.out.persistence.entity.os;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "regra_operacao_os")
public class RegraOperacaoOs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String codigo;
    @Column(nullable=false) private String descricao;
    @Column(name="permissao_execucao") private String permissaoExecucao;
    @Column(name="exige_autorizacao", nullable=false) private boolean exigeAutorizacao;
    @Column(name="permissao_autorizacao") private String permissaoAutorizacao;
    @Column(name="exige_outro_usuario", nullable=false) private boolean exigeOutroUsuario;
    @Column(name="metodo_autorizacao", nullable=false) private String metodoAutorizacao;
    @Column(nullable=false) private boolean ativo = true;
    private String observacao;
    @Column(name="criado_em", nullable=false) private OffsetDateTime criadoEm;
    @Column(name="atualizado_em", nullable=false) private OffsetDateTime atualizadoEm;

    public RegraOperacaoOs() {}
    @PrePersist void pre(){ if(criadoEm==null) criadoEm=OffsetDateTime.now(); if(atualizadoEm==null) atualizadoEm=criadoEm; }
    @PreUpdate void upd(){ atualizadoEm=OffsetDateTime.now(); }
    public Long getId(){return id;} public String getCodigo(){return codigo;} public String getDescricao(){return descricao;}
    public String getPermissaoExecucao(){return permissaoExecucao;} public boolean isExigeAutorizacao(){return exigeAutorizacao;}
    public String getPermissaoAutorizacao(){return permissaoAutorizacao;} public boolean isExigeOutroUsuario(){return exigeOutroUsuario;}
    public String getMetodoAutorizacao(){return metodoAutorizacao;} public boolean isAtivo(){return ativo;} public String getObservacao(){return observacao;}
    public void setCodigo(String v){codigo=v;} public void setDescricao(String v){descricao=v;} public void setPermissaoExecucao(String v){permissaoExecucao=v;}
    public void setExigeAutorizacao(boolean v){exigeAutorizacao=v;} public void setPermissaoAutorizacao(String v){permissaoAutorizacao=v;}
    public void setExigeOutroUsuario(boolean v){exigeOutroUsuario=v;} public void setMetodoAutorizacao(String v){metodoAutorizacao=v;}
    public void setAtivo(boolean v){ativo=v;} public void setObservacao(String v){observacao=v;}
}

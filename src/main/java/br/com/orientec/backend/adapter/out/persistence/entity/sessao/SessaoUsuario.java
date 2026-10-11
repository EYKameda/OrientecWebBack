package br.com.orientec.backend.adapter.out.persistence.entity.sessao;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Table(name="sessao_usuario")
public class SessaoUsuario {
    @Id private UUID id;
    @Column(name="usuario_codigo",nullable=false) private Long usuarioCodigo;
    @Column(name="sessao_estacao_id",nullable=false) private UUID sessaoEstacaoId;
    @Column(name="criada_em",nullable=false) private OffsetDateTime criadaEm;
    @Column(name="ultimo_acesso_em",nullable=false) private OffsetDateTime ultimoAcessoEm;
    @Column(name="expira_em",nullable=false) private OffsetDateTime expiraEm;
    @Column(name="encerrada_em") private OffsetDateTime encerradaEm;
    @Column(name="motivo_encerramento") private String motivoEncerramento;
    protected SessaoUsuario() {}
    public static SessaoUsuario nova() { return new SessaoUsuario(); }
    public UUID getId(){return id;} public Long getUsuarioCodigo(){return usuarioCodigo;} public UUID getSessaoEstacaoId(){return sessaoEstacaoId;} public OffsetDateTime getCriadaEm(){return criadaEm;} public OffsetDateTime getUltimoAcessoEm(){return ultimoAcessoEm;} public OffsetDateTime getExpiraEm(){return expiraEm;} public OffsetDateTime getEncerradaEm(){return encerradaEm;} public String getMotivoEncerramento(){return motivoEncerramento;}
    public void setId(UUID v){id=v;} public void setUsuarioCodigo(Long v){usuarioCodigo=v;} public void setSessaoEstacaoId(UUID v){sessaoEstacaoId=v;} public void setCriadaEm(OffsetDateTime v){criadaEm=v;} public void setUltimoAcessoEm(OffsetDateTime v){ultimoAcessoEm=v;} public void setExpiraEm(OffsetDateTime v){expiraEm=v;} public void setEncerradaEm(OffsetDateTime v){encerradaEm=v;} public void setMotivoEncerramento(String v){motivoEncerramento=v;}
}

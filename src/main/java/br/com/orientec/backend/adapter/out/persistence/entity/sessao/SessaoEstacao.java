package br.com.orientec.backend.adapter.out.persistence.entity.sessao;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Table(name="sessao_estacao")
public class SessaoEstacao {
    @Id private UUID id;
    @Column(name="estacao_codigo",nullable=false) private Long estacaoCodigo;
    @Column(name="criada_em",nullable=false) private OffsetDateTime criadaEm;
    @Column(name="ultimo_heartbeat_em",nullable=false) private OffsetDateTime ultimoHeartbeatEm;
    @Column(name="expira_em",nullable=false) private OffsetDateTime expiraEm;
    @Column(name="encerrada_em") private OffsetDateTime encerradaEm;
    @Column(name="motivo_encerramento") private String motivoEncerramento;
    @Column(name="ip_local",length=45) private String ipLocal;
    @Column(name="ip_publico",length=45) private String ipPublico;
    protected SessaoEstacao() {}
    public static SessaoEstacao nova() { return new SessaoEstacao(); }
    public UUID getId(){return id;} public Long getEstacaoCodigo(){return estacaoCodigo;} public OffsetDateTime getCriadaEm(){return criadaEm;} public OffsetDateTime getUltimoHeartbeatEm(){return ultimoHeartbeatEm;} public OffsetDateTime getExpiraEm(){return expiraEm;} public OffsetDateTime getEncerradaEm(){return encerradaEm;} public String getMotivoEncerramento(){return motivoEncerramento;}
    public String getIpLocal(){return ipLocal;} public String getIpPublico(){return ipPublico;}
    public void setId(UUID v){id=v;} public void setEstacaoCodigo(Long v){estacaoCodigo=v;} public void setCriadaEm(OffsetDateTime v){criadaEm=v;} public void setUltimoHeartbeatEm(OffsetDateTime v){ultimoHeartbeatEm=v;} public void setExpiraEm(OffsetDateTime v){expiraEm=v;} public void setEncerradaEm(OffsetDateTime v){encerradaEm=v;} public void setMotivoEncerramento(String v){motivoEncerramento=v;} public void setIpLocal(String v){ipLocal=v;} public void setIpPublico(String v){ipPublico=v;}
}

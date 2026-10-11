package br.com.orientec.backend.adapter.out.persistence.entity.sessao;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Table(name="codigo_lancamento_estacao")
public class CodigoLancamentoEstacao {
    @Id private UUID id;
    @Column(name="sessao_estacao_id",nullable=false) private UUID sessaoEstacaoId;
    @Column(name="codigo_hash",nullable=false,unique=true,length=64) private String codigoHash;
    @Column(name="criado_em",nullable=false) private OffsetDateTime criadoEm;
    @Column(name="expira_em",nullable=false) private OffsetDateTime expiraEm;
    @Column(name="utilizado_em") private OffsetDateTime utilizadoEm;
    protected CodigoLancamentoEstacao() {}
    public static CodigoLancamentoEstacao novo() { return new CodigoLancamentoEstacao(); }
    public UUID getId(){return id;} public UUID getSessaoEstacaoId(){return sessaoEstacaoId;} public String getCodigoHash(){return codigoHash;} public OffsetDateTime getCriadoEm(){return criadoEm;} public OffsetDateTime getExpiraEm(){return expiraEm;} public OffsetDateTime getUtilizadoEm(){return utilizadoEm;}
    public void setId(UUID v){id=v;} public void setSessaoEstacaoId(UUID v){sessaoEstacaoId=v;} public void setCodigoHash(String v){codigoHash=v;} public void setCriadoEm(OffsetDateTime v){criadoEm=v;} public void setExpiraEm(OffsetDateTime v){expiraEm=v;} public void setUtilizadoEm(OffsetDateTime v){utilizadoEm=v;}
}

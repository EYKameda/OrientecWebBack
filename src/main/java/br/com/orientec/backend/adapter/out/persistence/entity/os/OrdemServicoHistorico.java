package br.com.orientec.backend.adapter.out.persistence.entity.os;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="ordem_servico_historico")
public class OrdemServicoHistorico {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="ordem_servico_id",nullable=false) private Long ordemServicoId;
    @Column(name="situacao_id") private Short situacaoId;
    @Column(name="usuario_codigo") private Long usuarioCodigo;
    @Column(name="data_hora",nullable=false) private OffsetDateTime dataHora;
    @Column(nullable=false) private String tipo;
    @Column(nullable=false) private String descricao;
    protected OrdemServicoHistorico(){}
    public OrdemServicoHistorico(Long os,Short situacao,Long usuario,String tipo,String descricao){
        this.ordemServicoId=os; this.situacaoId=situacao; this.usuarioCodigo=usuario;
        this.dataHora=OffsetDateTime.now(); this.tipo=tipo; this.descricao=descricao;
    }

    public Long getId(){return id;}
    public Long getOrdemServicoId(){return ordemServicoId;}
    public Short getSituacaoId(){return situacaoId;}
    public Long getUsuarioCodigo(){return usuarioCodigo;}
    public OffsetDateTime getDataHora(){return dataHora;}
    public String getTipo(){return tipo;}
    public String getDescricao(){return descricao;}
}

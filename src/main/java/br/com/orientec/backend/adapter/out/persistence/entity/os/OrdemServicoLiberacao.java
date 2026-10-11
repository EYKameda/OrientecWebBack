package br.com.orientec.backend.adapter.out.persistence.entity.os;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity @Table(name="ordem_servico_liberacao")
public class OrdemServicoLiberacao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="ordem_servico_id",nullable=false) private Long ordemServicoId;
 @Column(name="regra_operacao_id",nullable=false) private Long regraOperacaoId;
 @Column(name="usuario_solicitante_codigo",nullable=false) private Long solicitanteCodigo;
 @Column(name="usuario_autorizador_codigo",nullable=false) private Long autorizadorCodigo;
 @Column(name="data_hora",nullable=false) private OffsetDateTime dataHora;
 @Column(name="metodo_autorizacao",nullable=false) private String metodoAutorizacao;
 @Column(name="referencia_chave") private String referenciaChave;
 @Column(name="valor_anterior") private BigDecimal valorAnterior;
 @Column(name="valor_novo") private BigDecimal valorNovo;
 @Column(nullable=false) private String justificativa;
 protected OrdemServicoLiberacao(){}
 public OrdemServicoLiberacao(Long os,Long regra,Long solicitante,Long autorizador,String metodo,String referencia,BigDecimal anterior,BigDecimal novo,String justificativa){
  this.ordemServicoId=os;this.regraOperacaoId=regra;this.solicitanteCodigo=solicitante;this.autorizadorCodigo=autorizador;this.metodoAutorizacao=metodo;this.referenciaChave=referencia;this.valorAnterior=anterior;this.valorNovo=novo;this.justificativa=justificativa;this.dataHora=OffsetDateTime.now();
 }
 public Long getId(){return id;} public Long getOrdemServicoId(){return ordemServicoId;} public Long getRegraOperacaoId(){return regraOperacaoId;} public Long getSolicitanteCodigo(){return solicitanteCodigo;} public Long getAutorizadorCodigo(){return autorizadorCodigo;} public OffsetDateTime getDataHora(){return dataHora;} public String getMetodoAutorizacao(){return metodoAutorizacao;} public String getReferenciaChave(){return referenciaChave;} public BigDecimal getValorAnterior(){return valorAnterior;} public BigDecimal getValorNovo(){return valorNovo;} public String getJustificativa(){return justificativa;}
}

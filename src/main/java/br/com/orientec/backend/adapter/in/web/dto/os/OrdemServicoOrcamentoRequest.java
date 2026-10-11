package br.com.orientec.backend.adapter.in.web.dto.os;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrdemServicoOrcamentoRequest {
    private Long id;
    private Long ordemServicoId;
    private java.math.BigDecimal valorPecas;
    private java.math.BigDecimal valorServico;
    private java.math.BigDecimal desconto;
    private java.math.BigDecimal valorTotal;
    private java.time.OffsetDateTime dataOrcamento;
    private Long usuarioCodigo;
    private String observacao;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public Long getOrdemServicoId() { return ordemServicoId; }
    public void setOrdemServicoId(Long value) { this.ordemServicoId=value; }
    public java.math.BigDecimal getValorPecas() { return valorPecas; }
    public void setValorPecas(java.math.BigDecimal value) { this.valorPecas=value; }
    public java.math.BigDecimal getValorServico() { return valorServico; }
    public void setValorServico(java.math.BigDecimal value) { this.valorServico=value; }
    public java.math.BigDecimal getDesconto() { return desconto; }
    public void setDesconto(java.math.BigDecimal value) { this.desconto=value; }
    public java.math.BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(java.math.BigDecimal value) { this.valorTotal=value; }
    public java.time.OffsetDateTime getDataOrcamento() { return dataOrcamento; }
    public void setDataOrcamento(java.time.OffsetDateTime value) { this.dataOrcamento=value; }
    public Long getUsuarioCodigo() { return usuarioCodigo; }
    public void setUsuarioCodigo(Long value) { this.usuarioCodigo=value; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String value) { this.observacao=value; }
}

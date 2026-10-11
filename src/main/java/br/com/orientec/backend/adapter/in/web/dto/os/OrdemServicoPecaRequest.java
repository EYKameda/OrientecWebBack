package br.com.orientec.backend.adapter.in.web.dto.os;

import java.math.BigDecimal;

public class OrdemServicoPecaRequest {
    private Long id;
    private Long ordemServicoId;
    private Integer sequencia;
    private Integer itemId;
    private Integer pedidoId;
    private String descricaoSnapshot;
    private Integer fornecedorId;
    private java.math.BigDecimal precoUnitario;
    private java.math.BigDecimal precoVenda;
    private java.math.BigDecimal quantidade;
    private java.math.BigDecimal total;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public Long getOrdemServicoId() { return ordemServicoId; }
    public void setOrdemServicoId(Long value) { this.ordemServicoId=value; }
    public Integer getSequencia() { return sequencia; }
    public void setSequencia(Integer value) { this.sequencia=value; }
    public Integer getItemId() { return itemId; }
    public void setItemId(Integer value) { this.itemId=value; }
    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer value) { this.pedidoId=value; }
    public String getDescricaoSnapshot() { return descricaoSnapshot; }
    public void setDescricaoSnapshot(String value) { this.descricaoSnapshot=value; }
    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer value) { this.fornecedorId=value; }
    public java.math.BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(java.math.BigDecimal value) { this.precoUnitario=value; }
    public java.math.BigDecimal getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(java.math.BigDecimal value) { this.precoVenda=value; }
    public java.math.BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(java.math.BigDecimal value) { this.quantidade=value; }
    public java.math.BigDecimal getTotal() { return total; }
    public void setTotal(java.math.BigDecimal value) { this.total=value; }
}

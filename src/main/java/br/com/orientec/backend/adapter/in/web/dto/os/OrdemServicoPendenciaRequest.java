package br.com.orientec.backend.adapter.in.web.dto.os;

import java.time.OffsetDateTime;

public class OrdemServicoPendenciaRequest {
    private Long id;
    private Long ordemServicoId;
    private Short tipoPendenciaId;
    private String descricao;
    private java.time.OffsetDateTime dataInclusao;
    private java.time.OffsetDateTime dataResolucao;
    private boolean resolvida;
    private Long usuarioCodigo;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public Long getOrdemServicoId() { return ordemServicoId; }
    public void setOrdemServicoId(Long value) { this.ordemServicoId=value; }
    public Short getTipoPendenciaId() { return tipoPendenciaId; }
    public void setTipoPendenciaId(Short value) { this.tipoPendenciaId=value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao=value; }
    public java.time.OffsetDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(java.time.OffsetDateTime value) { this.dataInclusao=value; }
    public java.time.OffsetDateTime getDataResolucao() { return dataResolucao; }
    public void setDataResolucao(java.time.OffsetDateTime value) { this.dataResolucao=value; }
    public boolean isResolvida() { return resolvida; }
    public void setResolvida(boolean value) { this.resolvida=value; }
    public Long getUsuarioCodigo() { return usuarioCodigo; }
    public void setUsuarioCodigo(Long value) { this.usuarioCodigo=value; }
}

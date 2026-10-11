package br.com.orientec.backend.adapter.in.web.dto.os;

import java.time.OffsetDateTime;


public class OrdemServicoHistoricoResponse {
    private Long id;
    private Long ordemServicoId;
    private Short situacaoId;
    private Long usuarioCodigo;
    private OffsetDateTime dataHora;
    private String tipo;
    private String descricao;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public Long getOrdemServicoId() { return ordemServicoId; }
    public void setOrdemServicoId(Long value) { this.ordemServicoId=value; }
    public Short getSituacaoId() { return situacaoId; }
    public void setSituacaoId(Short value) { this.situacaoId=value; }
    public Long getUsuarioCodigo() { return usuarioCodigo; }
    public void setUsuarioCodigo(Long value) { this.usuarioCodigo=value; }
    public OffsetDateTime getDataHora() { return dataHora; }
    public void setDataHora(OffsetDateTime value) { this.dataHora=value; }
    public String getTipo() { return tipo; }
    public void setTipo(String value) { this.tipo=value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao=value; }
}

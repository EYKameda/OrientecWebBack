package br.com.orientec.backend.adapter.in.web.dto.os;


public class OrdemServicoRequest {
    private Integer clienteId;
    private Integer equipamentoModeloId;
    private Short origemId;
    private Short tipoGarantiaId;
    private Long responsavelCodigo;
    private Long sedeAtualCodigo;
    private String defeitoRelatado;
    private String observacao;
    private boolean aguardaPecas;
    private boolean aguardaFotos;
    private boolean aguardaNota;
    private boolean envioCredenciado;
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer value) { this.clienteId=value; }
    public Integer getEquipamentoModeloId() { return equipamentoModeloId; }
    public void setEquipamentoModeloId(Integer value) { this.equipamentoModeloId=value; }
    public Short getOrigemId() { return origemId; }
    public void setOrigemId(Short value) { this.origemId=value; }
    public Short getTipoGarantiaId() { return tipoGarantiaId; }
    public void setTipoGarantiaId(Short value) { this.tipoGarantiaId=value; }
    public Long getResponsavelCodigo() { return responsavelCodigo; }
    public void setResponsavelCodigo(Long value) { this.responsavelCodigo=value; }
    public Long getSedeAtualCodigo() { return sedeAtualCodigo; }
    public void setSedeAtualCodigo(Long value) { this.sedeAtualCodigo=value; }
    public String getDefeitoRelatado() { return defeitoRelatado; }
    public void setDefeitoRelatado(String value) { this.defeitoRelatado=value; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String value) { this.observacao=value; }
    public boolean isAguardaPecas() { return aguardaPecas; }
    public void setAguardaPecas(boolean value) { this.aguardaPecas=value; }
    public boolean isAguardaFotos() { return aguardaFotos; }
    public void setAguardaFotos(boolean value) { this.aguardaFotos=value; }
    public boolean isAguardaNota() { return aguardaNota; }
    public void setAguardaNota(boolean value) { this.aguardaNota=value; }
    public boolean isEnvioCredenciado() { return envioCredenciado; }
    public void setEnvioCredenciado(boolean value) { this.envioCredenciado=value; }
}

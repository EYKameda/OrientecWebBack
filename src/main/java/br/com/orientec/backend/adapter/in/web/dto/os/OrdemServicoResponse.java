package br.com.orientec.backend.adapter.in.web.dto.os;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;


public class OrdemServicoResponse {
    private Long id;
    private Integer clienteId;
    private Integer equipamentoModeloId;
    private Short origemId;
    private Short tipoGarantiaId;
    private Long responsavelCodigo;
    private Long sedeAtualCodigo;
    private Long criadoPor;
    private LocalDate dataAbertura;
    private LocalTime horaAbertura;
    private String defeitoRelatado;
    private String observacao;
    private boolean aguardaPecas;
    private boolean aguardaFotos;
    private boolean aguardaNota;
    private boolean envioCredenciado;
    private OffsetDateTime dataEnvioCredenciado;
    private OffsetDateTime dataEntrega;
    private Long entreguePor;
    private OffsetDateTime criadoEm;
    private OffsetDateTime atualizadoEm;
    private Long version;
    private OsStatusResponse situacao;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
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
    public Long getCriadoPor() { return criadoPor; }
    public void setCriadoPor(Long value) { this.criadoPor=value; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate value) { this.dataAbertura=value; }
    public LocalTime getHoraAbertura() { return horaAbertura; }
    public void setHoraAbertura(LocalTime value) { this.horaAbertura=value; }
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
    public OffsetDateTime getDataEnvioCredenciado() { return dataEnvioCredenciado; }
    public void setDataEnvioCredenciado(OffsetDateTime value) { this.dataEnvioCredenciado=value; }
    public OffsetDateTime getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(OffsetDateTime value) { this.dataEntrega=value; }
    public Long getEntreguePor() { return entreguePor; }
    public void setEntreguePor(Long value) { this.entreguePor=value; }
    public OffsetDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(OffsetDateTime value) { this.criadoEm=value; }
    public OffsetDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(OffsetDateTime value) { this.atualizadoEm=value; }
    public Long getVersion() { return version; }
    public void setVersion(Long value) { this.version=value; }
    public OsStatusResponse getSituacao() { return situacao; }
    public void setSituacao(OsStatusResponse value) { this.situacao=value; }
}

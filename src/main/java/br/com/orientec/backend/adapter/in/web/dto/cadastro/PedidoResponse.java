package br.com.orientec.backend.adapter.in.web.dto.cadastro;
import java.time.LocalDate;
public class PedidoResponse {
    private Integer id;
    private Integer fornecedorId;
    private LocalDate dataPedido;
    private String situacao;
    private String observacao;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer value) { this.fornecedorId = value; }
    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate value) { this.dataPedido = value; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String value) { this.situacao = value; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String value) { this.observacao = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}

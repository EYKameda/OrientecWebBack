package br.com.orientec.backend.adapter.in.web.dto.cadastro;
public class ItemResponse {
    private Integer id;
    private String descricao;
    private String aplicacao;
    private String codigoBarras;
    private Integer familiaId;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao = value; }
    public String getAplicacao() { return aplicacao; }
    public void setAplicacao(String value) { this.aplicacao = value; }
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String value) { this.codigoBarras = value; }
    public Integer getFamiliaId() { return familiaId; }
    public void setFamiliaId(Integer value) { this.familiaId = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}

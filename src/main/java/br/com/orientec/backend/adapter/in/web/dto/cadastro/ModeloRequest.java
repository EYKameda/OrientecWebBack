package br.com.orientec.backend.adapter.in.web.dto.cadastro;
public class ModeloRequest {
    private Integer id;
    private Integer marcaId;
    private Integer linhaId;
    private String descricao;
    private String complemento;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public Integer getMarcaId() { return marcaId; }
    public void setMarcaId(Integer value) { this.marcaId = value; }
    public Integer getLinhaId() { return linhaId; }
    public void setLinhaId(Integer value) { this.linhaId = value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao = value; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String value) { this.complemento = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}

package br.com.orientec.backend.adapter.in.web.dto.cadastro;
public class FamiliaResponse {
    private Integer id;
    private String descricao;
    private boolean ativo;
    public Integer getId() { return id; }
    public void setId(Integer value) { this.id = value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao = value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo = value; }
}

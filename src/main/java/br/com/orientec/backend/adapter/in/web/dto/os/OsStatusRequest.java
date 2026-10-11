package br.com.orientec.backend.adapter.in.web.dto.os;


public class OsStatusRequest {
    private Short id;
    private String codigo;
    private String descricao;
    private boolean ativo;
    public Short getId() { return id; }
    public void setId(Short value) { this.id=value; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String value) { this.codigo=value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao=value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo=value; }
}

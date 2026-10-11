package br.com.orientec.backend.adapter.in.web.dto.usuario;


public class PermissaoResponse {
    private Long id;
    private String codigo;
    private String descricao;
    private String modulo;
    private boolean ativo;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String value) { this.codigo=value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao=value; }
    public String getModulo() { return modulo; }
    public void setModulo(String value) { this.modulo=value; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean value) { this.ativo=value; }
}

package br.com.orientec.backend.adapter.in.web.dto.os;


public class RegraTransicaoOsRequest {
    private Long id;
    private Short origemId;
    private Short destinoId;
    private Short minNivel;
    private boolean retrocesso;
    private boolean ativa;
    private String descricao;
    private String tipoTransicao;
    private boolean exigeAutorizacao;
    private String permissaoExecucao;
    private String permissaoAutorizacao;
    private boolean exigeOutroUsuario;
    private String metodoAutorizacao;
    public Long getId() { return id; }
    public void setId(Long value) { this.id=value; }
    public Short getOrigemId() { return origemId; }
    public void setOrigemId(Short value) { this.origemId=value; }
    public Short getDestinoId() { return destinoId; }
    public void setDestinoId(Short value) { this.destinoId=value; }
    public Short getMinNivel() { return minNivel; }
    public void setMinNivel(Short value) { this.minNivel=value; }
    public boolean isRetrocesso() { return retrocesso; }
    public void setRetrocesso(boolean value) { this.retrocesso=value; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean value) { this.ativa=value; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String value) { this.descricao=value; }
    public String getTipoTransicao() { return tipoTransicao; }
    public void setTipoTransicao(String value) { this.tipoTransicao=value; }
    public boolean isExigeAutorizacao() { return exigeAutorizacao; }
    public void setExigeAutorizacao(boolean value) { this.exigeAutorizacao=value; }
    public String getPermissaoExecucao() { return permissaoExecucao; }
    public void setPermissaoExecucao(String value) { this.permissaoExecucao=value; }
    public String getPermissaoAutorizacao() { return permissaoAutorizacao; }
    public void setPermissaoAutorizacao(String value) { this.permissaoAutorizacao=value; }
    public boolean isExigeOutroUsuario() { return exigeOutroUsuario; }
    public void setExigeOutroUsuario(boolean value) { this.exigeOutroUsuario=value; }
    public String getMetodoAutorizacao() { return metodoAutorizacao; }
    public void setMetodoAutorizacao(String value) { this.metodoAutorizacao=value; }
}

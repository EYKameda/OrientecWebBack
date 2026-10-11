package br.com.orientec.backend.adapter.in.web.dto.os;
public class RegraOperacaoOsResponse {
    private Long id; private String codigo, descricao, permissaoExecucao, permissaoAutorizacao, metodoAutorizacao, observacao; private boolean exigeAutorizacao, exigeOutroUsuario, ativo;
    public Long getId(){return id;} public void setId(Long v){id=v;} public String getCodigo(){return codigo;} public void setCodigo(String v){codigo=v;} public String getDescricao(){return descricao;} public void setDescricao(String v){descricao=v;}
    public String getPermissaoExecucao(){return permissaoExecucao;} public void setPermissaoExecucao(String v){permissaoExecucao=v;} public String getPermissaoAutorizacao(){return permissaoAutorizacao;} public void setPermissaoAutorizacao(String v){permissaoAutorizacao=v;}
    public String getMetodoAutorizacao(){return metodoAutorizacao;} public void setMetodoAutorizacao(String v){metodoAutorizacao=v;} public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
    public boolean isExigeAutorizacao(){return exigeAutorizacao;} public void setExigeAutorizacao(boolean v){exigeAutorizacao=v;} public boolean isExigeOutroUsuario(){return exigeOutroUsuario;} public void setExigeOutroUsuario(boolean v){exigeOutroUsuario=v;} public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
}

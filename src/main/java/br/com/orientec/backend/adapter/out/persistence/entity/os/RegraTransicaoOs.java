package br.com.orientec.backend.adapter.out.persistence.entity.os;

import jakarta.persistence.*;

@Entity
@Table(name="regra_transicao_os")
public class RegraTransicaoOs {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="situacao_origem_id",nullable=false) private Short origemId;
 @Column(name="situacao_destino_id",nullable=false) private Short destinoId;
 @Column(name="min_nivel") private Short minNivel;
 @Column(name="eh_retrocesso",nullable=false) private boolean retrocesso;
 @Column(nullable=false) private boolean ativa=true;
 private String descricao;
 @Column(name="tipo_transicao",nullable=false) private String tipoTransicao="NORMAL";
 @Column(name="exige_autorizacao",nullable=false) private boolean exigeAutorizacao;
 @Column(name="permissao_execucao") private String permissaoExecucao;
 @Column(name="permissao_autorizacao") private String permissaoAutorizacao;
 @Column(name="exige_outro_usuario",nullable=false) private boolean exigeOutroUsuario;
 @Column(name="metodo_autorizacao",nullable=false) private String metodoAutorizacao="NENHUM";
 public RegraTransicaoOs(){}
 public Long getId(){return id;} public Short getOrigemId(){return origemId;} public Short getDestinoId(){return destinoId;} public Short getMinNivel(){return minNivel;} public boolean isRetrocesso(){return retrocesso;} public boolean isAtiva(){return ativa;} public String getDescricao(){return descricao;} public String getTipoTransicao(){return tipoTransicao;} public boolean isExigeAutorizacao(){return exigeAutorizacao;} public String getPermissaoExecucao(){return permissaoExecucao;} public String getPermissaoAutorizacao(){return permissaoAutorizacao;} public boolean isExigeOutroUsuario(){return exigeOutroUsuario;} public String getMetodoAutorizacao(){return metodoAutorizacao;}
 public void setOrigemId(Short v){origemId=v;} public void setDestinoId(Short v){destinoId=v;} public void setMinNivel(Short v){minNivel=v;} public void setRetrocesso(boolean v){retrocesso=v;} public void setAtiva(boolean v){ativa=v;} public void setDescricao(String v){descricao=v;} public void setTipoTransicao(String v){tipoTransicao=v;} public void setExigeAutorizacao(boolean v){exigeAutorizacao=v;} public void setPermissaoExecucao(String v){permissaoExecucao=v;} public void setPermissaoAutorizacao(String v){permissaoAutorizacao=v;} public void setExigeOutroUsuario(boolean v){exigeOutroUsuario=v;} public void setMetodoAutorizacao(String v){metodoAutorizacao=v;}
}

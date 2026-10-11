package br.com.orientec.backend.adapter.out.persistence.entity.os;
import jakarta.persistence.*;
@Entity @Table(name="regra_condicao_os")
public class RegraCondicaoOs {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="regra_transicao_id",nullable=false) private Long regraTransicaoId;
 @Column(nullable=false) private String campo;
 @Column(nullable=false) private String operador;
 private String valor;
 @Column(nullable=false) private Short ordem=1;
 @Column(nullable=false) private boolean ativo=true;
 protected RegraCondicaoOs(){}
 public Long getId(){return id;} public Long getRegraTransicaoId(){return regraTransicaoId;} public String getCampo(){return campo;} public String getOperador(){return operador;} public String getValor(){return valor;} public Short getOrdem(){return ordem;} public boolean isAtivo(){return ativo;}
 public void setRegraTransicaoId(Long v){regraTransicaoId=v;} public void setCampo(String v){campo=v;} public void setOperador(String v){operador=v;} public void setValor(String v){valor=v;} public void setOrdem(Short v){ordem=v;} public void setAtivo(boolean v){ativo=v;}
}

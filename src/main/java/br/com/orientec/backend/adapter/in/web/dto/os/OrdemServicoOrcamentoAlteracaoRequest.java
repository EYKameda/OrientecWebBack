package br.com.orientec.backend.adapter.in.web.dto.os;
import java.math.BigDecimal;
public class OrdemServicoOrcamentoAlteracaoRequest {
 private BigDecimal valorPecas,valorServico,desconto,valorTotal; private String observacao,justificativa; private Long autorizadorCodigo; private String senhaAutorizador;
 public BigDecimal getValorPecas(){return valorPecas;} public void setValorPecas(BigDecimal v){valorPecas=v;} public BigDecimal getValorServico(){return valorServico;} public void setValorServico(BigDecimal v){valorServico=v;}
 public BigDecimal getDesconto(){return desconto;} public void setDesconto(BigDecimal v){desconto=v;} public BigDecimal getValorTotal(){return valorTotal;} public void setValorTotal(BigDecimal v){valorTotal=v;}
 public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;} public String getJustificativa(){return justificativa;} public void setJustificativa(String v){justificativa=v;}
 public Long getAutorizadorCodigo(){return autorizadorCodigo;} public void setAutorizadorCodigo(Long v){autorizadorCodigo=v;} public String getSenhaAutorizador(){return senhaAutorizador;} public void setSenhaAutorizador(String v){senhaAutorizador=v;}
}

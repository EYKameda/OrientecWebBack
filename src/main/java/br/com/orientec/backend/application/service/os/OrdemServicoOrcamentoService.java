package br.com.orientec.backend.application.service.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.os.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service
public class OrdemServicoOrcamentoService extends CrudApplicationService<OrdemServicoOrcamento,Long>{
 private final AutorizacaoOperacaoOsService autorizacao; private final OrdemServicoLiberacaoPersistencePort liberacoes; private final br.com.orientec.backend.adapter.out.persistence.repository.os.OrdemServicoOrcamentoRepository repository;
 public OrdemServicoOrcamentoService(CrudPersistencePort<OrdemServicoOrcamento,Long> r,AutorizacaoOperacaoOsService a,OrdemServicoLiberacaoPersistencePort l,br.com.orientec.backend.adapter.out.persistence.repository.os.OrdemServicoOrcamentoRepository repository){super(r);autorizacao=a;liberacoes=l;this.repository=repository;}
 @Transactional(readOnly=true) public List<OrdemServicoOrcamento> porOs(Long osId){ return repository.findByOrdemServicoId(osId); }
 @Transactional public OrdemServicoOrcamento alterarValores(Long id,Long solicitante,Long autorizador,String senha,String justificativa,BigDecimal pecas,BigDecimal servico,BigDecimal desconto,BigDecimal total,String observacao){
  OrdemServicoOrcamento atual=buscar(id); String operacao=detectarOperacao(atual,pecas,servico,desconto,total);
  var regra=autorizacao.validar(operacao,solicitante,autorizador,senha);
  if(justificativa==null||justificativa.isBlank()) throw new IllegalArgumentException("Justificativa obrigatória para alteração autorizada.");
  BigDecimal anterior=atual.getValorTotal();
  if(pecas!=null)atual.setValorPecas(pecas); if(servico!=null)atual.setValorServico(servico); if(desconto!=null)atual.setDesconto(desconto); if(total!=null)atual.setValorTotal(total); if(observacao!=null)atual.setObservacao(observacao);
  OrdemServicoOrcamento salvo=persistence.save(atual);
  liberacoes.save(new OrdemServicoLiberacao(atual.getOrdemServicoId(),regra.getId(),solicitante,autorizador,regra.getMetodoAutorizacao(),null,anterior,atual.getValorTotal(),justificativa.trim()));
  return salvo;
 }
 private String detectarOperacao(OrdemServicoOrcamento a,BigDecimal p,BigDecimal s,BigDecimal d,BigDecimal t){
  if(d!=null&&!d.equals(a.getDesconto())) return "ALTERAR_DESCONTO";
  if(p!=null&&!p.equals(a.getValorPecas())) return "ALTERAR_VALOR_PECA";
  if(s!=null&&!s.equals(a.getValorServico())) return "ALTERAR_VALOR_ORCAMENTO";
  if(t!=null&&!t.equals(a.getValorTotal())) return "ALTERAR_VALOR_TOTAL";
  throw new IllegalArgumentException("Nenhum valor do orçamento foi alterado.");
 }
}

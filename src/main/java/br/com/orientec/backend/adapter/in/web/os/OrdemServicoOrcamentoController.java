package br.com.orientec.backend.adapter.in.web.os;
import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.*;
import br.com.orientec.backend.adapter.in.web.mapper.os.OrdemServicoOrcamentoMapper;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoOrcamento;
import br.com.orientec.backend.application.service.os.OrdemServicoOrcamentoService;
import br.com.orientec.backend.security.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/v1/ordem_servico_orcamento") @PreAuthorize("hasAuthority('PERM_OS_ORCAMENTO')")
public class OrdemServicoOrcamentoController extends CrudController<OrdemServicoOrcamentoRequest,OrdemServicoOrcamentoResponse,OrdemServicoOrcamento,Long,OrdemServicoOrcamentoService>{
 private final OrdemServicoOrcamentoService budgetService;
 public OrdemServicoOrcamentoController(OrdemServicoOrcamentoService service,OrdemServicoOrcamentoMapper mapper){super(service,mapper);this.budgetService=service;}
 @Override protected String resourcePath(){return "ordem_servico_orcamento";} @Override protected Object idOf(OrdemServicoOrcamento e){return e.getId();}
 @GetMapping("/os/{osId}") public List<OrdemServicoOrcamentoResponse> porOs(@PathVariable Long osId){ return budgetService.porOs(osId).stream().map(((OrdemServicoOrcamentoMapper)mapper)::toResponse).toList(); }
 @Override
 @PutMapping("/{id}")
 public OrdemServicoOrcamentoResponse atualizar(@PathVariable Long id,@RequestBody OrdemServicoOrcamentoRequest body){
  throw new IllegalArgumentException("Alteração de valores do orçamento deve usar /{id}/valores, com liberação de outro acesso.");
 }

 @PutMapping("/{id}/valores") public OrdemServicoOrcamentoResponse alterarValores(@PathVariable Long id,@RequestBody OrdemServicoOrcamentoAlteracaoRequest r,Authentication authentication){
  var principal=(JwtService.JwtPrincipal)authentication.getPrincipal();
  var e=budgetService.alterarValores(id,principal.usuarioCodigo(),r.getAutorizadorCodigo(),r.getSenhaAutorizador(),r.getJustificativa(),r.getValorPecas(),r.getValorServico(),r.getDesconto(),r.getValorTotal(),r.getObservacao());
  return ((OrdemServicoOrcamentoMapper)mapper).toResponse(e);
 }
}

package br.com.orientec.backend.adapter.in.web.comunicacao;

import br.com.orientec.backend.adapter.in.web.dto.comunicacao.*;
import br.com.orientec.backend.application.service.comunicacao.ComunicacaoService;
import br.com.orientec.backend.security.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comunicacoes")
@PreAuthorize("hasAuthority('PERM_OS_COMUNICAR')")
public class ComunicacaoController {
    private final ComunicacaoService service;
    public ComunicacaoController(ComunicacaoService service){this.service=service;}
    @PostMapping("/enviar")
    public ComunicacaoResponse enviar(@RequestBody ComunicacaoRequest request, Authentication authentication){
        var principal=(JwtService.JwtPrincipal)authentication.getPrincipal();
        return ComunicacaoResponse.from(service.enviar(request, principal.usuarioCodigo()));
    }
    @GetMapping("/os/{osId}")
    public List<ComunicacaoResponse> listarOs(@PathVariable Long osId){return service.listarOs(osId).stream().map(ComunicacaoResponse::from).toList();}
    @GetMapping("/orcamento/{orcamentoId}")
    public List<ComunicacaoResponse> listarOrcamento(@PathVariable Long orcamentoId){return service.listarOrcamento(orcamentoId).stream().map(ComunicacaoResponse::from).toList();}
}

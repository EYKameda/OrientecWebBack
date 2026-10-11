package br.com.orientec.backend.application.service.comunicacao;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Cliente;
import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.*;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoOrcamento;
import br.com.orientec.backend.adapter.out.persistence.repository.cadastro.ClienteRepository;
import br.com.orientec.backend.adapter.out.persistence.repository.comunicacao.ComunicacaoRepository;
import br.com.orientec.backend.adapter.out.persistence.repository.os.OrdemServicoOrcamentoRepository;
import br.com.orientec.backend.adapter.out.persistence.repository.os.OrdemServicoRepository;
import br.com.orientec.backend.application.port.out.comunicacao.ComunicacaoGateway;
import br.com.orientec.backend.adapter.in.web.dto.comunicacao.ComunicacaoRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ComunicacaoService {
    private final ComunicacaoRepository repository; private final ClienteRepository clientes; private final OrdemServicoOrcamentoRepository orcamentos; private final OrdemServicoRepository ordens; private final ComunicacaoGateway gateway;
    public ComunicacaoService(ComunicacaoRepository repository, ClienteRepository clientes, OrdemServicoOrcamentoRepository orcamentos, OrdemServicoRepository ordens, ComunicacaoGateway gateway){this.repository=repository;this.clientes=clientes;this.orcamentos=orcamentos;this.ordens=ordens;this.gateway=gateway;}

    @Transactional
    public Comunicacao enviar(ComunicacaoRequest r, Long usuarioCodigo){
        if(r.getOrdemServicoId()==null && r.getOrcamentoId()==null) throw new IllegalArgumentException("Informe a OS ou o orçamento.");
        if(r.getCanal()==null) throw new IllegalArgumentException("Canal de comunicação obrigatório.");
        if(r.getMensagem()==null || r.getMensagem().isBlank()) throw new IllegalArgumentException("Mensagem obrigatória.");
        Integer clienteId; Long osId=r.getOrdemServicoId(), orcId=r.getOrcamentoId();
        if(orcId!=null){OrdemServicoOrcamento o=orcamentos.findById(orcId).orElseThrow(()->new IllegalArgumentException("Orçamento não encontrado.")); if(osId!=null && !osId.equals(o.getOrdemServicoId())) throw new IllegalArgumentException("Orçamento não pertence à OS informada."); osId=o.getOrdemServicoId();}
        if(osId==null) throw new IllegalArgumentException("OS obrigatória para o vínculo da comunicação.");
        // O relacionamento da OS é validado pelo serviço de consulta do cliente via id informado no orçamento/OS.
        // Como o orçamento não carrega cliente, o endpoint exige que a OS exista e recupera o cliente pelo vínculo abaixo.
        clienteId = obterClienteIdDaOs(osId);
        if(clienteId==null) throw new IllegalArgumentException("OS não possui cliente vinculado.");
        Cliente cliente=clientes.findById(clienteId).orElseThrow(()->new IllegalArgumentException("Cliente da OS não encontrado."));
        String destinatario=destinatario(cliente,r.getCanal());
        Comunicacao c=new Comunicacao(); c.setOrdemServicoId(osId); c.setOrcamentoId(orcId); c.setClienteId(clienteId); c.setCanal(r.getCanal()); c.setStatus(StatusComunicacao.SOLICITADO); c.setDataHora(OffsetDateTime.now()); c.setDestinatario(destinatario); c.setAssunto(r.getAssunto()); c.setMensagem(r.getMensagem().trim()); c.setUsuarioCodigo(usuarioCodigo); c=repository.save(c);
        try { gateway.enviar(c.getCanal(),destinatario,c.getAssunto(),c.getMensagem()); c.setStatus(StatusComunicacao.ENVIADO); c.setDataHoraEnvio(OffsetDateTime.now()); c.setErro(null); } catch(Exception e){ c.setStatus(StatusComunicacao.FALHA); c.setErro(e.getMessage()==null?e.getClass().getSimpleName():e.getMessage()); }
        return repository.save(c);
    }
    private Integer obterClienteIdDaOs(Long osId){return ordens.findById(osId).orElseThrow(()->new IllegalArgumentException("OS não encontrada.")).getClienteId();}
    private String destinatario(Cliente c, TipoCanalComunicacao canal){String d=switch(canal){case WHATSAPP,SMS -> c.getCelular()!=null&&!c.getCelular().isBlank()?c.getCelular():c.getTelefone(); case EMAIL -> c.getEmail();}; if(d==null||d.isBlank()) throw new IllegalArgumentException("Cliente não possui contato para o canal "+canal+"."); return d;}
    @Transactional(readOnly=true) public List<Comunicacao> listarOs(Long osId){return repository.findByOrdemServicoIdOrderByDataHoraDesc(osId);}
    @Transactional(readOnly=true) public List<Comunicacao> listarOrcamento(Long id){return repository.findByOrcamentoIdOrderByDataHoraDesc(id);}
}

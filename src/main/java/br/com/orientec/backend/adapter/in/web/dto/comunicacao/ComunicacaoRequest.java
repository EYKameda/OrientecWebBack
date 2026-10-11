package br.com.orientec.backend.adapter.in.web.dto.comunicacao;
import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.TipoCanalComunicacao;
public class ComunicacaoRequest {
    private Long ordemServicoId; private Long orcamentoId; private TipoCanalComunicacao canal; private String assunto; private String mensagem;
    public Long getOrdemServicoId(){return ordemServicoId;} public void setOrdemServicoId(Long v){ordemServicoId=v;}
    public Long getOrcamentoId(){return orcamentoId;} public void setOrcamentoId(Long v){orcamentoId=v;}
    public TipoCanalComunicacao getCanal(){return canal;} public void setCanal(TipoCanalComunicacao v){canal=v;}
    public String getAssunto(){return assunto;} public void setAssunto(String v){assunto=v;} public String getMensagem(){return mensagem;} public void setMensagem(String v){mensagem=v;}
}

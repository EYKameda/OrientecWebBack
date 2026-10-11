package br.com.orientec.backend.application.port.out.comunicacao;
import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.TipoCanalComunicacao;
public interface ComunicacaoGateway {
    void enviar(TipoCanalComunicacao canal, String destinatario, String assunto, String mensagem);
}

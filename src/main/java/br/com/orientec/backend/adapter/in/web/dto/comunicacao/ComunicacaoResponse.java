package br.com.orientec.backend.adapter.in.web.dto.comunicacao;
import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.*;
import java.time.OffsetDateTime;
public record ComunicacaoResponse(Long id, Long ordemServicoId, Long orcamentoId, Integer clienteId, TipoCanalComunicacao canal, StatusComunicacao status, OffsetDateTime dataHora, OffsetDateTime dataHoraEnvio, String destinatario, String assunto, String mensagem, Long usuarioCodigo, String erro) {
    public static ComunicacaoResponse from(Comunicacao c){return new ComunicacaoResponse(c.getId(),c.getOrdemServicoId(),c.getOrcamentoId(),c.getClienteId(),c.getCanal(),c.getStatus(),c.getDataHora(),c.getDataHoraEnvio(),c.getDestinatario(),c.getAssunto(),c.getMensagem(),c.getUsuarioCodigo(),c.getErro());}
}

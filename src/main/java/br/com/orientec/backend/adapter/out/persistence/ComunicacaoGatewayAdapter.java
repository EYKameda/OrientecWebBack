package br.com.orientec.backend.adapter.out.persistence;

import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.TipoCanalComunicacao;
import br.com.orientec.backend.application.port.out.comunicacao.ComunicacaoGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Map;

@Component
public class ComunicacaoGatewayAdapter implements ComunicacaoGateway {
    private final JavaMailSender mailSender; private final RestClient client=RestClient.builder().build();
    private final boolean emailEnabled, webhookEnabled; private final String emailFrom, whatsappUrl, smsUrl;
    public ComunicacaoGatewayAdapter(JavaMailSender mailSender,
        @Value("${orientec.comunicacao.email.enabled:false}") boolean emailEnabled,
        @Value("${orientec.comunicacao.email.from:}") String emailFrom,
        @Value("${orientec.comunicacao.webhook.enabled:false}") boolean webhookEnabled,
        @Value("${orientec.comunicacao.webhook.whatsapp-url:}") String whatsappUrl,
        @Value("${orientec.comunicacao.webhook.sms-url:}") String smsUrl){
        this.mailSender=mailSender;this.emailEnabled=emailEnabled;this.emailFrom=emailFrom;this.webhookEnabled=webhookEnabled;this.whatsappUrl=whatsappUrl;this.smsUrl=smsUrl;
    }
    @Override public void enviar(TipoCanalComunicacao canal,String destinatario,String assunto,String mensagem){
        if(canal==TipoCanalComunicacao.EMAIL){
            if(!emailEnabled) throw new IllegalStateException("E-mail não está configurado.");
            SimpleMailMessage mail=new SimpleMailMessage(); mail.setTo(destinatario); if(!emailFrom.isBlank())mail.setFrom(emailFrom); mail.setSubject(assunto==null||assunto.isBlank()?"Orientec - Comunicação":assunto); mail.setText(mensagem); mailSender.send(mail); return;
        }
        if(!webhookEnabled) throw new IllegalStateException("Webhook de comunicação não está configurado para "+canal+".");
        String url=canal==TipoCanalComunicacao.WHATSAPP?whatsappUrl:smsUrl; if(url==null||url.isBlank()) throw new IllegalStateException("URL do provedor não configurada para "+canal+".");
        client.post().uri(url).body(Map.of("destinatario",destinatario,"assunto",assunto==null?"":assunto,"mensagem",mensagem,"canal",canal.name())).retrieve().toBodilessEntity();
    }
}

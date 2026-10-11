package br.com.orientec.backend.adapter.out.persistence.entity.comunicacao;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="comunicacao", indexes={
    @Index(name="ix_comunicacao_os", columnList="ordem_servico_id,data_hora"),
    @Index(name="ix_comunicacao_orcamento", columnList="orcamento_id,data_hora")
})
public class Comunicacao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="ordem_servico_id") private Long ordemServicoId;
    @Column(name="orcamento_id") private Long orcamentoId;
    @Column(name="cliente_id", nullable=false) private Integer clienteId;
    @Enumerated(EnumType.STRING) @Column(name="canal", nullable=false, length=20) private TipoCanalComunicacao canal;
    @Enumerated(EnumType.STRING) @Column(name="status", nullable=false, length=20) private StatusComunicacao status;
    @Column(name="data_hora", nullable=false) private OffsetDateTime dataHora;
    @Column(name="data_hora_envio") private OffsetDateTime dataHoraEnvio;
    @Column(name="destinatario", nullable=false, length=200) private String destinatario;
    @Column(name="assunto", length=200) private String assunto;
    @Column(name="mensagem", nullable=false, columnDefinition="text") private String mensagem;
    @Column(name="usuario_codigo", nullable=false) private Long usuarioCodigo;
    @Column(name="erro", columnDefinition="text") private String erro;
    public Comunicacao() {}
    public Long getId(){return id;} public Long getOrdemServicoId(){return ordemServicoId;} public Long getOrcamentoId(){return orcamentoId;}
    public Integer getClienteId(){return clienteId;} public TipoCanalComunicacao getCanal(){return canal;} public StatusComunicacao getStatus(){return status;}
    public OffsetDateTime getDataHora(){return dataHora;} public OffsetDateTime getDataHoraEnvio(){return dataHoraEnvio;} public String getDestinatario(){return destinatario;}
    public String getAssunto(){return assunto;} public String getMensagem(){return mensagem;} public Long getUsuarioCodigo(){return usuarioCodigo;} public String getErro(){return erro;}
    public void setOrdemServicoId(Long v){ordemServicoId=v;} public void setOrcamentoId(Long v){orcamentoId=v;} public void setClienteId(Integer v){clienteId=v;}
    public void setCanal(TipoCanalComunicacao v){canal=v;} public void setStatus(StatusComunicacao v){status=v;} public void setDataHora(OffsetDateTime v){dataHora=v;}
    public void setDataHoraEnvio(OffsetDateTime v){dataHoraEnvio=v;} public void setDestinatario(String v){destinatario=v;} public void setAssunto(String v){assunto=v;}
    public void setMensagem(String v){mensagem=v;} public void setUsuarioCodigo(Long v){usuarioCodigo=v;} public void setErro(String v){erro=v;}
}

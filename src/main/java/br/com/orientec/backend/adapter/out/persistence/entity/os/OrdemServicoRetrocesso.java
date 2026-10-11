package br.com.orientec.backend.adapter.out.persistence.entity.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.adapter.out.persistence.repository.os.*;
import br.com.orientec.backend.application.service.os.*;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity @Table(name="ordem_servico_retrocesso")
public class OrdemServicoRetrocesso {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="ordem_servico_id",nullable=false) private Long ordemServicoId;
    @Column(name="situacao_origem_id",nullable=false) private Short origemId;
    @Column(name="situacao_destino_id",nullable=false) private Short destinoId;
    @Column(name="usuario_solicitante_codigo",nullable=false) private Long solicitanteCodigo;
    @Column(name="usuario_autorizador_codigo",nullable=false) private Long autorizadorCodigo;
    @Column(name="data_hora",nullable=false) private OffsetDateTime dataHora;
    @Column(nullable=false) private String justificativa;
    protected OrdemServicoRetrocesso(){}
    public OrdemServicoRetrocesso(Long os,Short origem,Short destino,Long solicitante,
                                   Long autorizador,String justificativa){
        this.ordemServicoId=os; this.origemId=origem; this.destinoId=destino;
        this.solicitanteCodigo=solicitante; this.autorizadorCodigo=autorizador;
        this.justificativa=justificativa; this.dataHora=OffsetDateTime.now();
    }
}

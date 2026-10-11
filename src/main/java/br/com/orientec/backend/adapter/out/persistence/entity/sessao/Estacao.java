package br.com.orientec.backend.adapter.out.persistence.entity.sessao;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="estacao", uniqueConstraints=@UniqueConstraint(name="uk_estacao_sede_maquina", columnNames={"sede_codigo","nome_maquina"}))
public class Estacao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long codigo;
    @Column(name="sede_codigo", nullable=false) private Long sedeCodigo;
    @Column(name="nome_maquina", nullable=false, length=120) private String nomeMaquina;
    @Column(name="ip_local", length=45) private String ipLocal;
    @Column(name="ip_publico", length=45) private String ipPublico;
    @Column(nullable=false) private boolean ativo=true;
    @Column(name="ultimo_heartbeat_em") private OffsetDateTime ultimoHeartbeatEm;
    protected Estacao() {}
    public static Estacao nova() { return new Estacao(); }
    public Long getCodigo(){return codigo;} public Long getSedeCodigo(){return sedeCodigo;} public String getNomeMaquina(){return nomeMaquina;}
    public String getIpLocal(){return ipLocal;} public String getIpPublico(){return ipPublico;} public boolean isAtivo(){return ativo;} public OffsetDateTime getUltimoHeartbeatEm(){return ultimoHeartbeatEm;}
    public void setSedeCodigo(Long v){sedeCodigo=v;} public void setNomeMaquina(String v){nomeMaquina=v;} public void setIpLocal(String v){ipLocal=v;} public void setIpPublico(String v){ipPublico=v;} public void setAtivo(boolean v){ativo=v;} public void setUltimoHeartbeatEm(OffsetDateTime v){ultimoHeartbeatEm=v;}
}

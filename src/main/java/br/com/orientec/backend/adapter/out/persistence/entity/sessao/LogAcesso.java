package br.com.orientec.backend.adapter.out.persistence.entity.sessao;
import jakarta.persistence.*; import java.time.OffsetDateTime;
@Entity @Table(name="log_acesso")
public class LogAcesso {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="ocorrido_em",nullable=false) private OffsetDateTime ocorridoEm;
 @Column(nullable=false,length=40) private String tipo; @Column(nullable=false,length=20) private String resultado;
 @Column(name="usuario_codigo") private Long usuarioCodigo; @Column(length=60) private String login; @Column(name="sede_codigo") private Long sedeCodigo; @Column(name="estacao_codigo") private Long estacaoCodigo;
 @Column(name="nome_maquina",length=120) private String nomeMaquina; @Column(name="ip_local",length=45) private String ipLocal; @Column(name="ip_publico",length=45) private String ipPublico; @Column(name="codigo_motivo",length=60) private String codigoMotivo; @Column(name="detalhe_tecnico") private String detalheTecnico;
 protected LogAcesso(){}
 public static LogAcesso novo(){return new LogAcesso();}
 public void setOcorridoEm(OffsetDateTime v){ocorridoEm=v;} public void setTipo(String v){tipo=v;} public void setResultado(String v){resultado=v;} public void setUsuarioCodigo(Long v){usuarioCodigo=v;} public void setLogin(String v){login=v;} public void setSedeCodigo(Long v){sedeCodigo=v;} public void setEstacaoCodigo(Long v){estacaoCodigo=v;} public void setNomeMaquina(String v){nomeMaquina=v;} public void setIpLocal(String v){ipLocal=v;} public void setIpPublico(String v){ipPublico=v;} public void setCodigoMotivo(String v){codigoMotivo=v;} public void setDetalheTecnico(String v){detalheTecnico=v;}
}

package br.com.orientec.backend.application.service.sessao;
import br.com.orientec.backend.adapter.out.persistence.entity.sessao.LogAcesso; import br.com.orientec.backend.adapter.out.persistence.repository.sessao.LogAcessoRepository; import org.springframework.stereotype.Service; import java.time.*;
@Service public class LogAcessoService { private final LogAcessoRepository repo; public LogAcessoService(LogAcessoRepository repo){this.repo=repo;}
 public void registrar(String tipo,String resultado,String codigoMotivo,Long usuario,Long sede,Long estacao,String login,String maquina,String ipLocal,String ipPublico,String detalhe){var l=LogAcesso.novo();l.setOcorridoEm(OffsetDateTime.now(ZoneOffset.UTC));l.setTipo(tipo);l.setResultado(resultado);l.setCodigoMotivo(codigoMotivo);l.setUsuarioCodigo(usuario);l.setSedeCodigo(sede);l.setEstacaoCodigo(estacao);l.setLogin(login);l.setNomeMaquina(maquina);l.setIpLocal(ipLocal);l.setIpPublico(ipPublico);l.setDetalheTecnico(detalhe);repo.save(l);}
}

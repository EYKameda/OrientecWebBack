package br.com.orientec.backend.application.port.out.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraCondicaoOs;
import java.util.List;
public interface RegraCondicaoPersistencePort { List<RegraCondicaoOs> findByRegraTransicaoIdAndAtivoTrueOrderByOrdem(Long regraId); }

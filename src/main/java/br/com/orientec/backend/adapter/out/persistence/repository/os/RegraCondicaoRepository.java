package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraCondicaoOs;
import br.com.orientec.backend.application.port.out.os.RegraCondicaoPersistencePort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RegraCondicaoRepository extends JpaRepository<RegraCondicaoOs,Long>, RegraCondicaoPersistencePort { List<RegraCondicaoOs> findByRegraTransicaoIdAndAtivoTrueOrderByOrdem(Long regraId); }

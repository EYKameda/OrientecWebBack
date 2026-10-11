package br.com.orientec.backend.adapter.out.persistence.repository.sessao;
import br.com.orientec.backend.adapter.out.persistence.entity.sessao.Estacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EstacaoRepository extends JpaRepository<Estacao,Long> { Optional<Estacao> findBySedeCodigoAndNomeMaquina(Long sedeCodigo,String nomeMaquina); }

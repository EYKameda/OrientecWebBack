package br.com.orientec.backend.adapter.out.persistence.repository.comunicacao;
import br.com.orientec.backend.adapter.out.persistence.entity.comunicacao.Comunicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ComunicacaoRepository extends JpaRepository<Comunicacao,Long> {
    List<Comunicacao> findByOrdemServicoIdOrderByDataHoraDesc(Long id);
    List<Comunicacao> findByOrcamentoIdOrderByDataHoraDesc(Long id);
}

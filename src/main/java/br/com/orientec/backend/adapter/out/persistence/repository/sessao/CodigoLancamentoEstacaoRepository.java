package br.com.orientec.backend.adapter.out.persistence.repository.sessao;
import br.com.orientec.backend.adapter.out.persistence.entity.sessao.CodigoLancamentoEstacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CodigoLancamentoEstacaoRepository extends JpaRepository<CodigoLancamentoEstacao,UUID> { Optional<CodigoLancamentoEstacao> findByCodigoHash(String codigoHash); }

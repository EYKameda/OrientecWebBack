package br.com.orientec.backend.adapter.out.persistence.repository.sessao;
import br.com.orientec.backend.adapter.out.persistence.entity.sessao.LogAcesso; import org.springframework.data.jpa.repository.JpaRepository;
public interface LogAcessoRepository extends JpaRepository<LogAcesso,Long>{}

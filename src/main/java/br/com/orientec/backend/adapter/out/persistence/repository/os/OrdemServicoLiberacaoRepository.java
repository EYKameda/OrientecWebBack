package br.com.orientec.backend.adapter.out.persistence.repository.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoLiberacao;
import br.com.orientec.backend.application.port.out.os.OrdemServicoLiberacaoPersistencePort;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdemServicoLiberacaoRepository extends JpaRepository<OrdemServicoLiberacao,Long>, OrdemServicoLiberacaoPersistencePort {}

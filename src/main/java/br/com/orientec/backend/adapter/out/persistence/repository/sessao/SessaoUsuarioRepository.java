package br.com.orientec.backend.adapter.out.persistence.repository.sessao;
import br.com.orientec.backend.adapter.out.persistence.entity.sessao.SessaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface SessaoUsuarioRepository extends JpaRepository<SessaoUsuario,UUID> {}

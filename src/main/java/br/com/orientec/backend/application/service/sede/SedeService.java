package br.com.orientec.backend.application.service.sede;

import br.com.orientec.backend.adapter.out.persistence.entity.sede.Sede;
import br.com.orientec.backend.application.port.out.sede.SedePersistencePort;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SedeService {
    private final SedePersistencePort repo;
    public SedeService(SedePersistencePort repo) { this.repo = repo; }

    @Transactional(readOnly = true)
    public PageResult<Sede> listar(PageQuery query) { return repo.findAll(query); }

    @Transactional(readOnly = true)
    public Sede buscar(Long id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Sede não encontrada.")); }

    @Transactional
    public Sede criar(Sede sede) { return repo.save(sede); }

    @Transactional
    public Sede atualizar(Long id, Sede dados) {
        Sede atual = buscar(id);
        atual.setDescricao(dados.getDescricao());
        atual.setSigla(dados.getSigla());
        atual.setAtivo(dados.isAtivo());
        return repo.save(atual);
    }

    @Transactional
    public void inativar(Long id) {
        Sede atual = buscar(id);
        atual.setAtivo(false);
        repo.save(atual);
    }
}

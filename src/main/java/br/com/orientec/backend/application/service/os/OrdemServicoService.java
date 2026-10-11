package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.SpringDataPageAdapter;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServico;
import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import br.com.orientec.backend.application.port.out.os.OrdemServicoPersistencePort;
import br.com.orientec.backend.application.port.out.os.OsStatusPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class OrdemServicoService {
    private final OrdemServicoPersistencePort repo;
    private final OsStatusPersistencePort status;

    public OrdemServicoService(OrdemServicoPersistencePort repo, OsStatusPersistencePort status) {
        this.repo = repo;
        this.status = status;
    }

    @Transactional(readOnly = true)
    public PageResult<OrdemServico> listar(PageQuery query, Short statusId, Integer clienteId) {
        var pageable = SpringDataPageAdapter.toPageable(query);
        if (statusId != null) {
            return SpringDataPageAdapter.toResult(repo.findBySituacao_Id(statusId, pageable));
        }
        if (clienteId != null) {
            return SpringDataPageAdapter.toResult(repo.findByClienteId(clienteId, pageable));
        }
        return repo.findAll(query);
    }

    @Transactional(readOnly = true)
    public OrdemServico buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("OS não encontrada."));
    }

    @Transactional
    public OrdemServico abrir(OrdemServico os, Long usuario) {
        if (os.getSituacao() == null) {
            os.setSituacao(status.findById((short) 1).orElseThrow());
        }
        os.setCriadoPor(usuario);
        if (os.getDataAbertura() == null) os.setDataAbertura(LocalDate.now());
        if (os.getHoraAbertura() == null) os.setHoraAbertura(LocalTime.now());
        return repo.save(os);
    }

    @Transactional
    public OrdemServico atualizar(Long id, OrdemServico b) {
        OrdemServico o = buscar(id);
        if (b.getClienteId() != null) o.setClienteId(b.getClienteId());
        o.setEquipamentoModeloId(b.getEquipamentoModeloId());
        o.setOrigemId(b.getOrigemId());
        o.setTipoGarantiaId(b.getTipoGarantiaId());
        o.setResponsavelCodigo(b.getResponsavelCodigo());
        o.setDefeitoRelatado(b.getDefeitoRelatado());
        o.setObservacao(b.getObservacao());
        o.setAguardaPecas(b.isAguardaPecas());
        o.setAguardaFotos(b.isAguardaFotos());
        o.setAguardaNota(b.isAguardaNota());
        o.setSedeAtualCodigo(b.getSedeAtualCodigo());
        return repo.save(o);
    }

    @Transactional
    public void excluir(Long id) {
        repo.deleteById(id);
    }
}

package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoOrcamento;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoOrcamentoRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoOrcamentoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdemServicoOrcamentoMapper extends ApiMapper<OrdemServicoOrcamentoRequest, OrdemServicoOrcamentoResponse, OrdemServicoOrcamento> {
    OrdemServicoOrcamentoResponse toResponse(OrdemServicoOrcamento entity);
    OrdemServicoOrcamento toEntity(OrdemServicoOrcamentoRequest request);
    void updateEntity(OrdemServicoOrcamentoRequest request, @MappingTarget OrdemServicoOrcamento entity);
}

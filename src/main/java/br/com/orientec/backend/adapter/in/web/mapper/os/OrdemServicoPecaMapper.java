package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoPeca;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPecaRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPecaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdemServicoPecaMapper extends ApiMapper<OrdemServicoPecaRequest, OrdemServicoPecaResponse, OrdemServicoPeca> {
    OrdemServicoPecaResponse toResponse(OrdemServicoPeca entity);
    OrdemServicoPeca toEntity(OrdemServicoPecaRequest request);
    void updateEntity(OrdemServicoPecaRequest request, @MappingTarget OrdemServicoPeca entity);
}

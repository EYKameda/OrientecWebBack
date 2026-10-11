package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoPendencia;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPendenciaRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoPendenciaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdemServicoPendenciaMapper extends ApiMapper<OrdemServicoPendenciaRequest, OrdemServicoPendenciaResponse, OrdemServicoPendencia> {
    OrdemServicoPendenciaResponse toResponse(OrdemServicoPendencia entity);
    OrdemServicoPendencia toEntity(OrdemServicoPendenciaRequest request);
    void updateEntity(OrdemServicoPendenciaRequest request, @MappingTarget OrdemServicoPendencia entity);
}

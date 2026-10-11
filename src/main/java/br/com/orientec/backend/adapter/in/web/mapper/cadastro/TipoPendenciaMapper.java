package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoPendencia;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoPendenciaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoPendenciaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TipoPendenciaMapper extends ApiMapper<TipoPendenciaRequest, TipoPendenciaResponse, TipoPendencia> {
    TipoPendenciaResponse toResponse(TipoPendencia entity);
    TipoPendencia toEntity(TipoPendenciaRequest request);
    void updateEntity(TipoPendenciaRequest request, @MappingTarget TipoPendencia entity);
}

package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoGarantia;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoGarantiaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.TipoGarantiaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TipoGarantiaMapper extends ApiMapper<TipoGarantiaRequest, TipoGarantiaResponse, TipoGarantia> {
    TipoGarantiaResponse toResponse(TipoGarantia entity);
    TipoGarantia toEntity(TipoGarantiaRequest request);
    void updateEntity(TipoGarantiaRequest request, @MappingTarget TipoGarantia entity);
}

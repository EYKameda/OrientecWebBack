package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Familia;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FamiliaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FamiliaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FamiliaMapper extends ApiMapper<FamiliaRequest, FamiliaResponse, Familia> {
    FamiliaResponse toResponse(Familia entity);
    Familia toEntity(FamiliaRequest request);
    void updateEntity(FamiliaRequest request, @MappingTarget Familia entity);
}

package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Marca;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.MarcaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.MarcaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MarcaMapper extends ApiMapper<MarcaRequest, MarcaResponse, Marca> {
    MarcaResponse toResponse(Marca entity);
    Marca toEntity(MarcaRequest request);
    void updateEntity(MarcaRequest request, @MappingTarget Marca entity);
}

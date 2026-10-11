package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Modelo;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ModeloRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ModeloResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModeloMapper extends ApiMapper<ModeloRequest, ModeloResponse, Modelo> {
    ModeloResponse toResponse(Modelo entity);
    Modelo toEntity(ModeloRequest request);
    void updateEntity(ModeloRequest request, @MappingTarget Modelo entity);
}

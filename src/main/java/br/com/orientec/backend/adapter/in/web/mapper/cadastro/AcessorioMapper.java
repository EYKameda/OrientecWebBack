package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Acessorio;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.AcessorioRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.AcessorioResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AcessorioMapper extends ApiMapper<AcessorioRequest, AcessorioResponse, Acessorio> {
    AcessorioResponse toResponse(Acessorio entity);
    Acessorio toEntity(AcessorioRequest request);
    void updateEntity(AcessorioRequest request, @MappingTarget Acessorio entity);
}

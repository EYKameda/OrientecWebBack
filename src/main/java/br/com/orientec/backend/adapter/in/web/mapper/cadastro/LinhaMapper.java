package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Linha;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.LinhaRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.LinhaResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LinhaMapper extends ApiMapper<LinhaRequest, LinhaResponse, Linha> {
    LinhaResponse toResponse(Linha entity);
    Linha toEntity(LinhaRequest request);
    void updateEntity(LinhaRequest request, @MappingTarget Linha entity);
}

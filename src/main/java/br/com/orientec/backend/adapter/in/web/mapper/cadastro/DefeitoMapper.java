package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Defeito;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.DefeitoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.DefeitoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DefeitoMapper extends ApiMapper<DefeitoRequest, DefeitoResponse, Defeito> {
    DefeitoResponse toResponse(Defeito entity);
    Defeito toEntity(DefeitoRequest request);
    void updateEntity(DefeitoRequest request, @MappingTarget Defeito entity);
}

package br.com.orientec.backend.adapter.in.web.mapper.sede;

import br.com.orientec.backend.adapter.in.web.dto.sede.SedeRequest;
import br.com.orientec.backend.adapter.in.web.dto.sede.SedeResponse;
import br.com.orientec.backend.adapter.out.persistence.entity.sede.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SedeMapper {
    @Mapping(target = "codigo", ignore = true)
    Sede toEntity(SedeRequest request);
    SedeResponse toResponse(Sede entity);
    @Mapping(target = "codigo", ignore = true)
    void update(SedeRequest request, @MappingTarget Sede entity);
}

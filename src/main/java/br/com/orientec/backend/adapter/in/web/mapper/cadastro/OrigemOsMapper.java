package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.OrigemOs;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.OrigemOsRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.OrigemOsResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrigemOsMapper extends ApiMapper<OrigemOsRequest, OrigemOsResponse, OrigemOs> {
    OrigemOsResponse toResponse(OrigemOs entity);
    OrigemOs toEntity(OrigemOsRequest request);
    void updateEntity(OrigemOsRequest request, @MappingTarget OrigemOs entity);
}

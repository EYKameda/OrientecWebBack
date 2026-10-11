package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraTransicaoOs;
import br.com.orientec.backend.adapter.in.web.dto.os.RegraTransicaoOsRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.RegraTransicaoOsResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegraTransicaoOsMapper extends ApiMapper<RegraTransicaoOsRequest, RegraTransicaoOsResponse, RegraTransicaoOs> {
    RegraTransicaoOsResponse toResponse(RegraTransicaoOs entity);
    RegraTransicaoOs toEntity(RegraTransicaoOsRequest request);
    void updateEntity(RegraTransicaoOsRequest request, @MappingTarget RegraTransicaoOs entity);
}

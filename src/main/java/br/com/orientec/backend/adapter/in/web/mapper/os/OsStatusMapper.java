package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OsStatus;
import br.com.orientec.backend.adapter.in.web.dto.os.OsStatusRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OsStatusResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OsStatusMapper extends ApiMapper<OsStatusRequest, OsStatusResponse, OsStatus> {
    OsStatusResponse toResponse(OsStatus entity);
    OsStatus toEntity(OsStatusRequest request);
    void updateEntity(OsStatusRequest request, @MappingTarget OsStatus entity);
}

package br.com.orientec.backend.adapter.in.web.mapper;

import org.mapstruct.MappingTarget;

public interface ApiMapper<REQ, RES, ENTITY> {
    RES toResponse(ENTITY entity);
    ENTITY toEntity(REQ request);
    void updateEntity(REQ request, @MappingTarget ENTITY entity);
}

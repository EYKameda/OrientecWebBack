package br.com.orientec.backend.adapter.in.web.mapper.usuario;

import br.com.orientec.backend.adapter.out.persistence.entity.usuario.Permissao;
import br.com.orientec.backend.adapter.in.web.dto.usuario.PermissaoRequest;
import br.com.orientec.backend.adapter.in.web.dto.usuario.PermissaoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissaoMapper extends ApiMapper<PermissaoRequest, PermissaoResponse, Permissao> {
    PermissaoResponse toResponse(Permissao entity);
    Permissao toEntity(PermissaoRequest request);
    void updateEntity(PermissaoRequest request, @MappingTarget Permissao entity);
}

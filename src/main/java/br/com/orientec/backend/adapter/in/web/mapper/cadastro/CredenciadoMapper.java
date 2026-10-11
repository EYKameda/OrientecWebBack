package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Credenciado;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.CredenciadoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.CredenciadoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CredenciadoMapper extends ApiMapper<CredenciadoRequest, CredenciadoResponse, Credenciado> {
    CredenciadoResponse toResponse(Credenciado entity);
    Credenciado toEntity(CredenciadoRequest request);
    void updateEntity(CredenciadoRequest request, @MappingTarget Credenciado entity);
}

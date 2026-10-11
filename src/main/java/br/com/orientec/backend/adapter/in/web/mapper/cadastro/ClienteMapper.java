package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Cliente;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ClienteRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ClienteResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper extends ApiMapper<ClienteRequest, ClienteResponse, Cliente> {
    ClienteResponse toResponse(Cliente entity);
    Cliente toEntity(ClienteRequest request);
    void updateEntity(ClienteRequest request, @MappingTarget Cliente entity);
}

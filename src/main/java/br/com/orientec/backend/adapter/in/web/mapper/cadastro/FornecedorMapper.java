package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Fornecedor;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FornecedorRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.FornecedorResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FornecedorMapper extends ApiMapper<FornecedorRequest, FornecedorResponse, Fornecedor> {
    FornecedorResponse toResponse(Fornecedor entity);
    Fornecedor toEntity(FornecedorRequest request);
    void updateEntity(FornecedorRequest request, @MappingTarget Fornecedor entity);
}

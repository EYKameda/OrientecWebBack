package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Pedido;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.PedidoRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.PedidoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoMapper extends ApiMapper<PedidoRequest, PedidoResponse, Pedido> {
    PedidoResponse toResponse(Pedido entity);
    Pedido toEntity(PedidoRequest request);
    void updateEntity(PedidoRequest request, @MappingTarget Pedido entity);
}

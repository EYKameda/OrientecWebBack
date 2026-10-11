package br.com.orientec.backend.adapter.in.web.mapper.cadastro;

import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Item;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ItemRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ItemResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper extends ApiMapper<ItemRequest, ItemResponse, Item> {
    ItemResponse toResponse(Item entity);
    Item toEntity(ItemRequest request);
    void updateEntity(ItemRequest request, @MappingTarget Item entity);
}

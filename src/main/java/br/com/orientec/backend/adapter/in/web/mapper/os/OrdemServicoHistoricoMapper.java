package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServicoHistorico;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoHistoricoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemServicoHistoricoMapper {
    OrdemServicoHistoricoResponse toResponse(OrdemServicoHistorico entity);
}

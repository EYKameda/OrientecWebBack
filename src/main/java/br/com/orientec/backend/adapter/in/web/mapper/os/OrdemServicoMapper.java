package br.com.orientec.backend.adapter.in.web.mapper.os;

import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.OrdemServicoResponse;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import br.com.orientec.backend.adapter.out.persistence.entity.os.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = OsStatusMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdemServicoMapper extends ApiMapper<OrdemServicoRequest, OrdemServicoResponse, OrdemServico> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "situacao", ignore = true)
    @Mapping(target = "criadoPor", ignore = true)
    @Mapping(target = "dataAbertura", ignore = true)
    @Mapping(target = "horaAbertura", ignore = true)
    @Mapping(target = "envioCredenciado", ignore = true)
    @Mapping(target = "dataEntrega", ignore = true)
    @Mapping(target = "entreguePor", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "version", ignore = true)
    OrdemServico toEntity(OrdemServicoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "situacao", ignore = true)
    @Mapping(target = "criadoPor", ignore = true)
    @Mapping(target = "dataAbertura", ignore = true)
    @Mapping(target = "horaAbertura", ignore = true)
    @Mapping(target = "envioCredenciado", ignore = true)
    @Mapping(target = "dataEntrega", ignore = true)
    @Mapping(target = "entreguePor", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(OrdemServicoRequest request, @MappingTarget OrdemServico entity);

    OrdemServicoResponse toResponse(OrdemServico entity);
}

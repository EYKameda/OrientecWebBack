package br.com.orientec.backend.adapter.in.web.mapper.os;
import br.com.orientec.backend.adapter.in.web.dto.os.*;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.adapter.in.web.mapper.ApiMapper;
import org.mapstruct.*;
@Mapper(componentModel="spring",nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface RegraOperacaoOsMapper extends ApiMapper<RegraOperacaoOsRequest,RegraOperacaoOsResponse,RegraOperacaoOs>{}

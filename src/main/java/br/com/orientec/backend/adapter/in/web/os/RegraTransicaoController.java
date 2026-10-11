package br.com.orientec.backend.adapter.in.web.os;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.RegraTransicaoOsRequest;
import br.com.orientec.backend.adapter.in.web.dto.os.RegraTransicaoOsResponse;
import br.com.orientec.backend.adapter.in.web.mapper.os.RegraTransicaoOsMapper;
import br.com.orientec.backend.application.service.os.RegraTransicaoService;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraTransicaoOs;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regras-transicao-os")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class RegraTransicaoController extends CrudController<
        RegraTransicaoOsRequest, RegraTransicaoOsResponse, RegraTransicaoOs, Long, RegraTransicaoService> {
    public RegraTransicaoController(RegraTransicaoService service, RegraTransicaoOsMapper mapper) {
        super(service, mapper);
    }
    @Override protected String resourcePath() { return "regras-transicao-os"; }
    @Override protected Object idOf(RegraTransicaoOs entity) { return entity.getId(); }
}

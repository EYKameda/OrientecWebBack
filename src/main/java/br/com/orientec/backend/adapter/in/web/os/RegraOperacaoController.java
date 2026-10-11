package br.com.orientec.backend.adapter.in.web.os;
import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.os.*;
import br.com.orientec.backend.adapter.in.web.mapper.os.RegraOperacaoOsMapper;
import br.com.orientec.backend.adapter.out.persistence.entity.os.RegraOperacaoOs;
import br.com.orientec.backend.application.service.os.RegraOperacaoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/regras-operacao-os")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class RegraOperacaoController extends CrudController<RegraOperacaoOsRequest,RegraOperacaoOsResponse,RegraOperacaoOs,Long,RegraOperacaoService>{
 public RegraOperacaoController(RegraOperacaoService service,RegraOperacaoOsMapper mapper){super(service,mapper);}
 @Override protected String resourcePath(){return "regras-operacao-os";} @Override protected Object idOf(RegraOperacaoOs e){return e.getId();}
}

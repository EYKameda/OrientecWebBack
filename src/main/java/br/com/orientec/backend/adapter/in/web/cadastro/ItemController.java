package br.com.orientec.backend.adapter.in.web.cadastro;

import br.com.orientec.backend.adapter.in.web.CrudController;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ItemRequest;
import br.com.orientec.backend.adapter.in.web.dto.cadastro.ItemResponse;
import br.com.orientec.backend.adapter.in.web.mapper.cadastro.ItemMapper;
import br.com.orientec.backend.application.service.cadastro.ItemService;
import br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Item;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@PreAuthorize("hasAuthority('PERM_OS_CONTROLAR')")
public class ItemController extends CrudController<
        ItemRequest, ItemResponse, Item, Integer, ItemService> {

    public ItemController(ItemService service, ItemMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected String resourcePath() {
        return "item";
    }

    @Override
    protected Object idOf(Item entity) {
        return entity.getId();
    }
}

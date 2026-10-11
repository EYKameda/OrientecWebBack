package br.com.orientec.backend.adapter.in.web.autocomplete;

import br.com.orientec.backend.application.service.autocomplete.AutocompleteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autocomplete")
@PreAuthorize("isAuthenticated()")
public class AutocompleteController {

    private final AutocompleteService service;

    public AutocompleteController(AutocompleteService service) {
        this.service = service;
    }

    @GetMapping("/{tipo}")
    public List<AutocompleteService.Option> buscar(
            @PathVariable String tipo,
            @RequestParam(defaultValue = "") String texto) {
        return service.buscar(tipo, texto);
    }
}

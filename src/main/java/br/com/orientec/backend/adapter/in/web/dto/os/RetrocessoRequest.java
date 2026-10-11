package br.com.orientec.backend.adapter.in.web.dto.os;
import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.adapter.out.persistence.repository.os.*;
import br.com.orientec.backend.application.service.os.*;

import jakarta.validation.constraints.*;

public record RetrocessoRequest(
    @NotNull Short destinoId,
    @NotNull Long autorizadorCodigo,
    @NotBlank String senhaAutorizador,
    @NotBlank String justificativa
) {}

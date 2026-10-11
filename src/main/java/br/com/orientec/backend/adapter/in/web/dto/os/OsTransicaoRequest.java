package br.com.orientec.backend.adapter.in.web.dto.os;

import jakarta.validation.constraints.NotBlank;

public record OsTransicaoRequest(
        Long autorizadorCodigo,
        String senhaAutorizador,
        @NotBlank String justificativa
) {}

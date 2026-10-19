package br.com.orientec.backend.adapter.in.web.dto.sede;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SedeRequest(
        @NotBlank @Size(max = 120) String descricao,
        @Size(max = 30) String sigla,
        Long empresaCodigo,
        boolean ativo) {}

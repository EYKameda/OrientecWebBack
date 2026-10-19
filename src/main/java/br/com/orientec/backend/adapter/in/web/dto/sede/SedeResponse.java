package br.com.orientec.backend.adapter.in.web.dto.sede;

public record SedeResponse(Long codigo, String descricao, String sigla, Long empresaCodigo, boolean ativo) {}

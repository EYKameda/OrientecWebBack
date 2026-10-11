package br.com.orientec.backend.adapter.in.web.dto.os;

public record OsAcaoResponse(
        String tipo,
        Short destinoId,
        String destinoCodigo,
        String destinoDescricao,
        boolean permitida,
        boolean exigeAutorizacao,
        String permissaoExecucao,
        String permissaoAutorizacao,
        boolean exigeOutroUsuario,
        String metodoAutorizacao,
        String mensagem
) {}

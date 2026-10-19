
package br.com.orientec.backend.application.service.os;

import br.com.orientec.backend.adapter.out.persistence.entity.os.*;
import br.com.orientec.backend.application.service.CrudApplicationService;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;
import br.com.orientec.backend.application.port.out.os.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoOrcamentoService
        extends CrudApplicationService<OrdemServicoOrcamento, Long> {

    private final AutorizacaoOperacaoOsService autorizacao;
    private final OrdemServicoLiberacaoPersistencePort liberacoes;
    private final br.com.orientec.backend.adapter.out.persistence.repository.os
            .OrdemServicoOrcamentoRepository repository;

    public OrdemServicoOrcamentoService(
            CrudPersistencePort<OrdemServicoOrcamento, Long> r,
            AutorizacaoOperacaoOsService a,
            OrdemServicoLiberacaoPersistencePort l,
            br.com.orientec.backend.adapter.out.persistence.repository.os
                    .OrdemServicoOrcamentoRepository repository) {
        super(r);
        this.autorizacao = a;
        this.liberacoes = l;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoOrcamento> porOs(Long osId) {
        return repository.findByOrdemServicoId(osId);
    }

    @Transactional
    public OrdemServicoOrcamento alterarValores(
            Long id,
            Long solicitante,
            Long autorizador,
            String senha,
            String justificativa,
            BigDecimal pecas,
            BigDecimal servico,
            BigDecimal desconto,
            BigDecimal total,
            String observacao) {

        OrdemServicoOrcamento atual = buscar(id);

        List<AlteracaoFinanceira> alteracoes = new ArrayList<>();

        adicionarSeAlterado(
                alteracoes, "ALTERAR_VALOR_PECA",
                atual.getValorPecas(), pecas);

        adicionarSeAlterado(
                alteracoes, "ALTERAR_VALOR_ORCAMENTO",
                atual.getValorServico(), servico);

        adicionarSeAlterado(
                alteracoes, "ALTERAR_DESCONTO",
                atual.getDesconto(), desconto);

        adicionarSeAlterado(
                alteracoes, "ALTERAR_VALOR_TOTAL",
                atual.getValorTotal(), total);

        if (alteracoes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Nenhum valor financeiro do orçamento foi alterado.");
        }

        if (justificativa == null || justificativa.isBlank()) {
            throw new IllegalArgumentException(
                    "Justificativa obrigatória para alteração do orçamento.");
        }

        /*
         * Primeira fase: validar TODAS as regras antes de alterar
         * ou persistir qualquer valor.
         */
        List<RegraOperacaoOs> regrasValidadas = new ArrayList<>();

        for (AlteracaoFinanceira alteracao : alteracoes) {
            RegraOperacaoOs regra = autorizacao.validar(
                    alteracao.codigo,
                    solicitante,
                    autorizador,
                    senha);

            regrasValidadas.add(regra);
        }

        /*
         * Segunda fase: aplicar os valores somente depois que
         * todas as validações tiverem sido concluídas.
         */
        for (AlteracaoFinanceira alteracao : alteracoes) {
            switch (alteracao.codigo) {
                case "ALTERAR_VALOR_PECA" ->
                        atual.setValorPecas(alteracao.novo);

                case "ALTERAR_VALOR_ORCAMENTO" ->
                        atual.setValorServico(alteracao.novo);

                case "ALTERAR_DESCONTO" ->
                        atual.setDesconto(alteracao.novo);

                case "ALTERAR_VALOR_TOTAL" ->
                        atual.setValorTotal(alteracao.novo);

                default -> throw new IllegalStateException(
                        "Operação financeira não tratada: "
                                + alteracao.codigo);
            }
        }

        if (observacao != null) {
            atual.setObservacao(observacao);
        }

        OrdemServicoOrcamento salvo = persistence.save(atual);

        /*
         * Terceira fase: registrar cada liberação exigida.
         * A transação abrange a alteração do orçamento e a auditoria.
         */
        for (int i = 0; i < alteracoes.size(); i++) {
            AlteracaoFinanceira alteracao = alteracoes.get(i);
            RegraOperacaoOs regra = regrasValidadas.get(i);

            if (regra.isExigeAutorizacao()) {
                liberacoes.save(new OrdemServicoLiberacao(
                        salvo.getOrdemServicoId(),
                        regra.getId(),
                        solicitante,
                        autorizador,
                        regra.getMetodoAutorizacao(),
                        null,
                        alteracao.anterior,
                        alteracao.novo,
                        justificativa.trim()));
            }
        }

        return salvo;
    }

    private void adicionarSeAlterado(
            List<AlteracaoFinanceira> alteracoes,
            String codigo,
            BigDecimal anterior,
            BigDecimal novo) {

        if (novo == null) {
            return;
        }

        boolean mudou = anterior == null
                || anterior.compareTo(novo) != 0;

        if (mudou) {
            alteracoes.add(
                    new AlteracaoFinanceira(codigo, anterior, novo));
        }
    }

    private static class AlteracaoFinanceira {
        private final String codigo;
        private final BigDecimal anterior;
        private final BigDecimal novo;

        private AlteracaoFinanceira(
                String codigo,
                BigDecimal anterior,
                BigDecimal novo) {
            this.codigo = codigo;
            this.anterior = anterior;
            this.novo = novo;
        }
    }
}

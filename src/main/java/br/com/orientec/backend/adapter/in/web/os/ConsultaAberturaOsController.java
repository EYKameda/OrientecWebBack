package br.com.orientec.backend.adapter.in.web.os;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Read-only projection used by the OS opening/lookup screen.
 * Uses existing OS authorities and does not alter the global security configuration.
 */
@RestController
@RequestMapping("/api/v1/consulta-abertura-os")
@PreAuthorize("hasAnyAuthority('PERM_OS_ABRIR','PERM_OS_CONSULTAR')")
public class ConsultaAberturaOsController {
    private final EntityManager entityManager;

    public ConsultaAberturaOsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/os/{id}")
    @Transactional(readOnly = true)
    public Map<String, Object> consultarOs(@PathVariable Long id) {
        List<Object[]> rows = entityManager.createNativeQuery("""
            SELECT os.id, st.descricao, os.cliente_id, c.nome, c.cpf_cnpj,
                   c.telefone, c.celular, c.email, c.endereco, c.numero,
                   c.complemento, c.bairro, c.cidade, c.uf, c.cep,
                   m.descricao, m.complemento, os.defeito_relatado, os.observacao
              FROM ordem_servico os
              LEFT JOIN os_status st ON st.id = os.situacao_id
              LEFT JOIN cliente c ON c.id = os.cliente_id
              LEFT JOIN modelo m ON m.id = os.equipamento_modelo_id
             WHERE os.id = :id
            """).setParameter("id", id).getResultList();

        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OS não encontrada.");
        }
        Object[] r = rows.get(0);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("osId", r[0]);
        result.put("situacaoDescricao", r[1]);
        result.put("clienteId", r[2]);
        result.put("clienteNome", r[3]);
        result.put("clienteDocumento", r[4]);
        result.put("telefone", r[5]);
        result.put("celular", r[6]);
        result.put("email", r[7]);
        result.put("endereco", r[8]);
        result.put("numero", r[9]);
        result.put("complemento", r[10]);
        result.put("bairro", r[11]);
        result.put("cidade", r[12]);
        result.put("uf", r[13]);
        result.put("cep", r[14]);
        result.put("aparelho", r[15]);
        result.put("aparelhoComplemento", r[16]);
        result.put("servicoExecutar", r[17]);
        result.put("observacao", r[18]);
        result.put("acessorios", consultarAcessorios(id));
        result.put("orcamentos", consultarOrcamentos(id));
        return result;
    }

    @GetMapping("/cliente/{id}")
    @Transactional(readOnly = true)
    public Map<String, Object> consultarCliente(@PathVariable Integer id) {
        List<Object[]> rows = entityManager.createNativeQuery("""
            SELECT id, nome, cpf_cnpj, telefone, celular, email, endereco,
                   numero, complemento, bairro, cidade, uf, cep, ativo
              FROM cliente
             WHERE id = :id
            """).setParameter("id", id).getResultList();

        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
        }
        Object[] r = rows.get(0);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", r[0]);
        result.put("nome", r[1]);
        result.put("cpfCnpj", r[2]);
        result.put("telefone", r[3]);
        result.put("celular", r[4]);
        result.put("email", r[5]);
        result.put("endereco", r[6]);
        result.put("numero", r[7]);
        result.put("complemento", r[8]);
        result.put("bairro", r[9]);
        result.put("cidade", r[10]);
        result.put("uf", r[11]);
        result.put("cep", r[12]);
        result.put("ativo", r[13]);
        return result;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> consultarAcessorios(Long osId) {
        List<Object[]> rows = entityManager.createNativeQuery("""
            SELECT a.id, a.descricao
              FROM ordem_servico_acessorio osa
              JOIN acessorio a ON a.id = osa.acessorio_id
             WHERE osa.ordem_servico_id = :id
             ORDER BY a.descricao
            """).setParameter("id", osId).getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] r : rows) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r[0]);
            item.put("descricao", r[1]);
            result.add(item);
        }
        return result;
    }

    private List<Map<String, Object>> consultarOrcamentos(Long osId) {
        List<Object[]> rows = entityManager.createNativeQuery("""
            SELECT id, ordem_servico_id, valor_pecas, valor_servico, desconto,
                   valor_total, data_orcamento, observacao
              FROM ordem_servico_orcamento
             WHERE ordem_servico_id = :id
             ORDER BY data_orcamento DESC, id DESC
            """).setParameter("id", osId).getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] r : rows) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r[0]);
            item.put("ordemServicoId", r[1]);
            item.put("valorPecas", r[2]);
            item.put("valorServico", r[3]);
            item.put("desconto", r[4]);
            item.put("valorTotal", r[5]);
            item.put("dataOrcamento", r[6]);
            item.put("observacao", r[7]);
            result.add(item);
        }
        return result;
    }
}

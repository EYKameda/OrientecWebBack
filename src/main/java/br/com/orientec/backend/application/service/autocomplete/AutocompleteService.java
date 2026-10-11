package br.com.orientec.backend.application.service.autocomplete;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AutocompleteService {

    private final EntityManager entityManager;
    private final int minChars;
    private final int limit;

    public AutocompleteService(EntityManager entityManager,
                               @Value("${orientec.autocomplete.min-chars:2}") int minChars,
                               @Value("${orientec.autocomplete.limit:20}") int limit) {
        this.entityManager = entityManager;
        this.minChars = Math.max(1, minChars);
        this.limit = Math.max(1, Math.min(limit, 100));
    }

    private record Spec(Class<?> entityType, String idField, String labelField,
                        List<String> searchFields, String activeField) {}

    private static final Map<String, Spec> SPECS = buildSpecs();

    private static Map<String, Spec> buildSpecs() {
        Map<String, Spec> m = new LinkedHashMap<>();
        m.put("cliente", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Cliente.class,
                "id", "nome", List.of("nome", "cpfCnpj", "telefone", "celular", "email"), "ativo"));
        m.put("fornecedor", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Fornecedor.class,
                "id", "nome", List.of("nome", "fantasia", "cnpj", "cpf"), "ativo"));
        m.put("credenciado", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Credenciado.class,
                "id", "nome", List.of("nome", "fantasia", "cnpj", "cpf"), "ativo"));
        m.put("acessorio", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Acessorio.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("defeito", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Defeito.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("familia", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Familia.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("item", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Item.class,
                "id", "descricao", List.of("descricao", "codigoBarras", "aplicacao"), "ativo"));
        m.put("linha", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Linha.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("marca", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Marca.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("modelo", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.Modelo.class,
                "id", "descricao", List.of("descricao", "complemento"), "ativo"));
        m.put("origem-os", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.OrigemOs.class,
                "id", "descricao", List.of("descricao"), "ativo"));
        m.put("tipo-garantia", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoGarantia.class,
                "id", "descricao", List.of("codigo", "descricao"), "ativo"));
        m.put("tipo-pendencia", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.cadastro.TipoPendencia.class,
                "id", "descricao", List.of("codigo", "descricao"), "ativo"));
        m.put("sede", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.sede.Sede.class,
                "codigo", "descricao", List.of("descricao", "sigla"), "ativo"));
        m.put("usuario", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.usuario.Usuario.class,
                "codigo", "nome", List.of("nome", "apelido", "login"), "ativo"));
        m.put("os-status", new Spec(br.com.orientec.backend.adapter.out.persistence.entity.os.OsStatus.class,
                "id", "descricao", List.of("codigo", "descricao"), "ativo"));
        return Collections.unmodifiableMap(m);
    }

    public record Option(Object id, String label) {}

    @Transactional(readOnly = true)
    public List<Option> buscar(String tipo, String texto) {
        String normalizedType = tipo == null ? "" : tipo.trim().toLowerCase(Locale.ROOT);
        String normalizedText = texto == null ? "" : texto.trim();
        if (normalizedText.length() < minChars) return List.of();

        Spec spec = SPECS.get(normalizedType);
        if (spec == null) {
            throw new IllegalArgumentException("Tipo de busca não suportado: " + tipo);
        }

        List<Option> startsWith = consultar(spec, normalizedText, true);
        return !startsWith.isEmpty() ? startsWith : consultar(spec, normalizedText, false);
    }

    public int minChars() { return minChars; }
    public int limit() { return limit; }

    private List<Option> consultar(Spec spec, String texto, boolean prefixo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<?> root = cq.from(spec.entityType());

        String lowerText = texto.toLowerCase(Locale.ROOT);
        String pattern = prefixo ? lowerText + "%" : "%" + lowerText + "%";

        List<Predicate> matches = new ArrayList<>();
        List<Expression<String>> normalizedFields = new ArrayList<>();
        for (String field : spec.searchFields()) {
            Expression<String> value = cb.lower(root.get(field).as(String.class));
            normalizedFields.add(value);
            matches.add(cb.like(value, pattern));
        }

        Predicate where = cb.or(matches.toArray(Predicate[]::new));
        if (spec.activeField() != null) {
            where = cb.and(where, cb.isTrue(root.get(spec.activeField())));
        }

        Expression<String> label = root.get(spec.labelField()).as(String.class);
        cq.multiselect(root.get(spec.idField()), label)
                .where(where)
                .orderBy(cb.asc(cb.lower(label)), cb.asc(root.get(spec.idField())));

        return entityManager.createQuery(cq)
                .setMaxResults(limit)
                .getResultList()
                .stream()
                .map(row -> new Option(row[0], row[1] == null ? "" : row[1].toString()))
                .toList();
    }
}

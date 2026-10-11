# Status de validação — Orientec Backend V3

## Implementado
- Núcleo de autenticação JWT.
- Permissões efetivas por usuário.
- Autorização no backend via Spring Security.
- Regras de transição armazenadas no banco e consultadas no servidor.
- Retrocesso com solicitante e autorizador distintos.
- Senha do autorizador validada no backend.
- Permissão `OS_RETROCEDER` obrigatória para o autorizador.
- Justificativa obrigatória.
- Registro separado do solicitante e autorizador em `ordem_servico_retrocesso`.
- Histórico geral da OS.
- Lock pessimista da OS + `@Version`.
- Idempotência para o comando de retrocesso.
- CRUDs principais de cadastros e núcleo da OS.

## Validação do ambiente de geração
O ambiente usado para montar este pacote não possui Maven nem Docker disponíveis, portanto não foi possível executar `mvn test`/`mvn package` aqui.

A validação estrutural foi feita sobre os fontes e migrations. A primeira execução deve ser feita no ambiente local com Java 21 e Maven, apontando para PostgreSQL 17.

## Banco
O Flyway deve executar V1, V2, V3, V4 e V5 em ordem.
`ddl-auto=validate` permanece habilitado para impedir que o Hibernate altere o schema.

## Próxima etapa técnica
Antes de homologação, executar no ambiente local:

```bash
mvn clean test
mvn clean package
```

Depois validar a API contra uma cópia do banco Orientec migrada, principalmente:
- abertura de OS;
- consulta e filtros;
- retrocessos 4→3, 6→3, 9→3, 10→3 e 8→10;
- dupla autorização;
- histórico;
- concorrência;
- idempotência;
- migração dos dados legados.

# Orientec V3 — arquitetura e regra de fluxo

## Fonte de verdade das ações
A matriz PostgreSQL (`os_status` + `regra_transicao_os` + `regra_condicao_os`) é a fonte de verdade das transições.

O Angular não possui uma lista de transições hardcoded. Ele consulta `GET /api/v1/os/{id}/acoes` e somente renderiza as ações retornadas como permitidas.

O backend revalida tudo no momento da execução:
- situação atual;
- regra ativa;
- nível;
- permissão;
- autorização de outro acesso;
- senha do autorizador;
- condições configuradas;
- lock pessimista da OS;
- idempotência.

## Segurança
JWT + Spring Security + permissões efetivas. A UI é apenas uma camada de usabilidade; não é a fronteira de segurança. Spring Security suporta autorização por request e por método, permitindo defesa em profundidade. Consulte a documentação oficial: https://docs.spring.io/spring-security/reference/servlet/authorization/

## Idempotência
Toda transição exige `Idempotency-Key`. A mesma chave com o mesmo fingerprint é reaproveitada; a mesma chave com fingerprint diferente é rejeitada.

## Resiliência
Resilience4j fica disponível para integrações externas. Não usamos retry/circuit breaker para mascarar falhas transacionais do PostgreSQL.

## SISAGE
As situações e transições só devem ser adicionadas à matriz como `comprovadas` quando houver evidência no EXE/banco. A situação 11 é `ENVIADO PARA CREDENCIADO` e o dump atual analisado possui 2.061 OS nessa situação.

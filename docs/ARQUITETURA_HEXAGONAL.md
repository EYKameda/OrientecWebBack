# Orientec V3 — Arquitetura Hexagonal

## Objetivo

Esta versão parte do último backend entregue e reorganiza o projeto para separar:

- entrada HTTP;
- DTOs da API;
- regras/aplicação;
- portas de entrada/saída;
- adaptadores de persistência;
- segurança;
- configuração;
- banco/migrations.

A regra principal é que os controllers não retornam entidades JPA diretamente.

## Estrutura

```text
br.com.orientec.backend
├── adapter
│   ├── in
│   │   └── web
│   │       ├── cadastro
│   │       ├── os
│   │       ├── usuario
│   │       ├── idempotency
│   │       ├── dto
│   │       └── mapper
│   └── out
│       └── persistence
│           ├── entity
│           └── repository
├── application
│   ├── port
│   │   ├── in
│   │   └── out
│   └── service
├── security
├── config
└── api
```

## Fluxo de um CRUD

```text
HTTP
  |
  v
Controller
  |
  v
Request DTO
  |
  v
MapStruct
  |
  v
Application Service
  |
  v
Output Port
  |
  v
Spring Data Repository
  |
  v
JPA Entity
  |
  v
PostgreSQL
```

Na resposta:

```text
PostgreSQL
  |
  v
JPA Entity
  |
  v
Output Port
  |
  v
Application Service
  |
  v
MapStruct
  |
  v
Response DTO
  |
  v
HTTP
```

## MapStruct

Os DTOs de entrada/saída ficam no adaptador HTTP.

Exemplo:

```text
adapter/in/web/dto/cadastro/ClienteRequest
adapter/in/web/dto/cadastro/ClienteResponse

adapter/in/web/mapper/cadastro/ClienteMapper
```

O `ClienteController` não expõe `Cliente` como JSON.

O mesmo padrão foi aplicado aos principais cadastros e ao núcleo HTTP da OS.

## Portas de saída

O acesso à persistência passa por interfaces da aplicação.

Exemplos:

```text
application.port.out.CrudPersistencePort
application.port.out.UsuarioAuthPort
application.port.out.UsuarioPermissionPort
application.port.out.IdempotencyPersistencePort

application.port.out.os.OrdemServicoPersistencePort
application.port.out.os.OsStatusPersistencePort
application.port.out.os.RegraTransicaoPersistencePort
application.port.out.os.OrdemServicoRetrocessoPersistencePort
application.port.out.os.OrdemServicoHistoricoPersistencePort
```

As interfaces Spring Data ficam em:

```text
adapter.out.persistence.repository
```

e implementam essas portas.

## Núcleo da OS

O fluxo operacional continua separado do CRUD comum.

```text
OrdemServicoController
        |
        v
OrdemServicoService
        |
        +--> OrdemServicoPersistencePort
        +--> OsStatusPersistencePort
        |
        v
PostgreSQL
```

Retrocesso:

```text
RetrocessoController
        |
        v
RetrocessoService
        |
        +--> regra de transição
        +--> autenticação do autorizador
        +--> OS_RETROCEDER
        +--> solicitante != autorizador
        +--> justificativa obrigatória
        +--> lock pessimista
        +--> idempotência
        +--> histórico
        +--> auditoria do retrocesso
```

## Segurança

A segurança permanece em uma camada própria:

```text
security/
├── AuthController
├── AuthService
├── JwtService
├── JwtAuthenticationFilter
└── SecurityConfig
```

A autenticação utiliza portas de saída para buscar usuário e permissões.

## Persistência

As entidades JPA não fazem parte da API HTTP.

```text
adapter/out/persistence/entity
adapter/out/persistence/repository
```

O PostgreSQL continua sendo acessado por Spring Data JPA.

Flyway continua sendo a fonte de evolução do schema.

## Limite importante

A separação API ↔ persistência foi aplicada de forma efetiva com DTO + MapStruct.

O modelo JPA ainda é utilizado como modelo interno em parte dos serviços da aplicação. A próxima evolução arquitetural pode introduzir agregados de domínio puros para os contextos em que as regras são mais complexas, especialmente OS, retrocesso, orçamento e estoque.

Isso é intencional nesta etapa: não criar centenas de classes de domínio artificiais apenas para duplicar estruturas do banco legado.

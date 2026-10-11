# API principal

Base: `/api/v1`

## Swagger / OpenAPI
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Autenticação: botão **Authorize** → colar o token JWT (esquema Bearer).

## Rotas públicas (sem JWT)
- `GET /ping`
- `POST /auth/login`
- `POST /stations/handshake`
- `POST /stations/exchange`
- `GET /stations/session`
- `POST /stations/heartbeat`
- `GET /actuator/health`, `GET /actuator/info` (fora de `/api/v1`)

## Autenticação
- `POST /auth/login`
- `POST /auth/logout`

## OS
- `GET /os`
- `GET /os/{id}`
- `POST /os`
- `PUT /os/{id}`
- `DELETE /os/{id}`
- `GET /os/{osId}/historico`
- `GET /os/{osId}/acoes`
- `POST /os/{osId}/transicoes/{destinoId}`
- `POST /os/{osId}/retrocesso`

## Orçamento da OS
- `GET /ordem_servico_orcamento/os/{osId}`
- `PUT /ordem_servico_orcamento/{id}`
- `PUT /ordem_servico_orcamento/{id}/valores`

## Comunicações
- `POST /comunicacoes/enviar`
- `GET /comunicacoes/os/{osId}`
- `GET /comunicacoes/orcamento/{orcamentoId}`

## Usuários e sedes
- `/usuarios` (CRUD) e `GET|PUT /usuarios/{id}/permissoes`
- `/sedes` (CRUD; `DELETE` inativa)

## Autocomplete
- `GET /autocomplete/{tipo}`

## Idempotência (teste)
- `POST /idempotency/test`

## CRUD padrão
Cada recurso expõe `GET /recurso`, `POST /recurso`, `GET|PUT|DELETE /recurso/{id}`:

- Cadastros: `/cliente`, `/fornecedor`, `/credenciado`, `/marca`, `/linha`, `/modelo`, `/familia`, `/item`, `/defeito`, `/acessorio`, `/origem_os`, `/tipo_garantia`, `/tipo_pendencia`, `/pedido`
- Ligados à OS: `/ordem_servico_pendencia`, `/ordem_servico_peca`, `/ordem_servico_orcamento`
- Regras/segurança: `/os-status`, `/regras-operacao-os`, `/regras-transicao-os`, `/permissao`

Os endpoints administrativos usam `OS_CONTROLAR`. Consulta e abertura de OS usam as permissões específicas do núcleo.

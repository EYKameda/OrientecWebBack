# Consulta de dados para abertura de OS

Foi adicionado `ConsultaAberturaOsController`, com endpoints somente de leitura:
- `GET /api/v1/consulta-abertura-os/os/{id}`: OS, cliente, modelo, acessórios vinculados e orçamentos.
- `GET /api/v1/consulta-abertura-os/cliente/{id}`: dados de contato/endereço do cliente.

Ambos exigem uma autoridade já existente: `PERM_OS_ABRIR` ou `PERM_OS_CONSULTAR`. A configuração global de segurança não foi alterada. A criação de clientes permanece no controller existente e segue exigindo `PERM_OS_CONTROLAR`.

O endpoint consulta tabelas que já existem nas migrações (`ordem_servico`, `cliente`, `modelo`, `ordem_servico_acessorio`, `acessorio`, `ordem_servico_orcamento`, `os_status`); não cria nem modifica tabelas.

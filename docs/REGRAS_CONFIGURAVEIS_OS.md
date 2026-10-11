# Regras configuráveis da OS

A situação da OS e as regras de transição são dados de configuração do sistema, não constantes hardcoded no Java.

## Tabelas

- `os_status`: catálogo das situações. Nunca excluir fisicamente.
- `regra_transicao_os`: origem -> destino e requisitos.
- `regra_operacao_os`: operações que podem exigir autorização sem mudança de situação.
- `ordem_servico_liberacao`: auditoria das liberações.

## Segurança

O Controller não decide se uma operação pode ser executada. A decisão deve passar pelo serviço de aplicação/núcleo, que consulta as regras e valida as permissões.

O frontend pode ocultar ações, mas não é a autoridade de segurança.

## Importante

A regra de alteração de valores com outro acesso foi implementada como requisito do novo Orientec, enquanto a identificação exata da antiga "chave caixa" permanece pendente de comprovação no SISAGE.

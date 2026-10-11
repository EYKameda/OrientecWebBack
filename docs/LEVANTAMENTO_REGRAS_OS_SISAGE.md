# Levantamento de regras da Ordem de Serviço — SISAGE

## Situação 11

A análise do executável `SISAGE.exe` encontrou explicitamente a descrição:

`ENVIADO PARA CREDENCIADO`

A mesma sequência de strings aparece junto das descrições das situações da OS e dos campos `credenciado`, `credenciado_nome` e `data_1`.

No dump MySQL atual, a tabela `os` contém **29 registros com `situacao = 11`**.

A lista de OS identificada no dump é:

7012, 7022, 7087, 7088, 7092, 7549, 7623, 7662, 7668, 7690, 7730, 7774, 7818, 7939, 7954, 8078, 8109, 8111, 8127, 8192, 8194, 8288, 8350, 8364, 8542, 8657, 8658, 8692, 8713.

## Situações comprovadas no executável

1. AGUARDANDO ORÇAMENTO
2. AGUARDANDO ORÇAMENTO C/PENDÊNCIA
3. AGUARDANDO APROVAÇÃO
4. TESTE / LIMPEZA
5. APROVADO / EM EXECUÇÃO
6. SEM CONSERTO / PEÇAS
7. RETIRADO SEM APROVAÇÃO
8. RETIRADO
9. NÃO APRESENTOU DEFEITO
10. EXECUTADO
11. ENVIADO PARA CREDENCIADO

As situações 11+ não devem ser inventadas. A situação 11 agora está comprovada; as demais continuam pendentes de levantamento.

## Regras observadas no executável

O EXE contém operações que alteram diretamente `os.situacao`, incluindo:

- 1 -> 2
- 2 -> 1
- 1 -> 9
- 3 -> 4
- 1 -> 6
- 3 -> 6
- 3 -> 5
- 4 -> 10
- 5 -> 3
- 6 -> 3
- 9 -> 3
- 10 -> 3
- 10 -> 7
- 8 -> 10

Também há operações específicas de retrocesso que limpam campos de datas/logins anteriores.

A matriz definitiva de autorização ainda deve ser tratada como configurável, porque a presença de uma operação no EXE não prova, isoladamente, que ela seja uma transição normal ou uma operação que exige liberação.

## Alteração de valores

O EXE contém a mensagem:

`Confirma o valor de desconto acima do máximo estabelecido?`

Também há a rotina `liberar_orcamento` e mensagens relacionadas à liberação do orçamento/garantia.

Isso comprova que existe conceito de liberação associado ao orçamento/desconto no legado, mas **não comprova ainda qual é a chave, qual acesso autoriza, ou qual nível exato é exigido**.

Por isso o novo backend foi preparado com uma regra genérica de operação/autorização. As regras novas para alteração de valores foram marcadas na migration como **regra do novo Orientec**, e não como regra historicamente comprovada do SISAGE.

## Regra arquitetural adotada

### Situação

- pode cadastrar;
- pode alterar descrição/configuração;
- pode ativar/inativar;
- não pode excluir fisicamente.

### Regra de transição

Define:

- origem;
- destino;
- tipo (`NORMAL`, `RETROCESSO`, `EXCECAO`);
- exige autorização;
- permissão de execução;
- permissão de autorização;
- exige outro usuário;
- método de autorização;
- ativa/inativa.

### Regra de operação

Permite configurar operações que não são simplesmente mudança de situação, por exemplo:

- `ALTERAR_VALOR_ORCAMENTO`
- `ALTERAR_DESCONTO`
- `ALTERAR_VALOR_PECA`
- `ALTERAR_VALOR_TOTAL`

Para essas regras, o novo Orientec exige, por padrão, outro acesso com `OS_AUTORIZAR_EXCEPCIONAL` e senha do autorizador.

### Histórico de liberação

As liberações ficam registradas em `ordem_servico_liberacao`, contendo solicitante, autorizador, operação, data/hora, método, valores anterior/novo e justificativa.

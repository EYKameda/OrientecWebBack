# Regra de sede — SISAGE / Orientec V3

A autenticação valida a sede cadastrada no acesso contra a sede configurada para a instância do Orientec.

Fluxo:

1. localizar usuário pelo login;
2. validar usuário ativo e senha;
3. obter `usuario.sede_codigo`;
4. obter `InstanciaConfigPort.getSedeCodigo()`;
5. exigir igualdade;
6. em caso de divergência, bloquear a autenticação com a mensagem `Este usuário não está na Sede correta, verifique.`;
7. somente depois emitir o JWT.

No SISAGE antigo, a identificação da sede vinha da configuração/arquivo da instalação. No web, isso foi abstraído como `ORIENTEC_SEDE_CODIGO`, para não acoplar o domínio a um arquivo físico no computador do usuário.

## Configuração

```text
ORIENTEC_SEDE_CODIGO=1
```

O código `1` acima é apenas exemplo. O projeto não cria sede fictícia nem presume os códigos reais do legado.

## Sede x sede atual da OS

- `usuario.sede_codigo`: sede autorizada do usuário para autenticação;
- `ORIENTEC_SEDE_CODIGO`: sede da instância em que o usuário está entrando;
- `ordem_servico.sede_atual_codigo`: localização atual da OS.

Esses conceitos não devem ser confundidos.

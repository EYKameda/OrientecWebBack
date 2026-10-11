# Sessão de estação e sessão de usuário

## Estação / PMA

O `OrientecPMA.exe` lê somente:

```text
C:\Orientec\orientec.env

ORIENTEC_SEDE_CODIGO=2
```

Não há senha de estação nesse arquivo.

O PMA chama `POST /api/v1/stations/handshake` com:

```json
{
  "sedeCodigo": 2,
  "nomeMaquina": "PC-03",
  "ipLocal": "192.168.1.103"
}
```

O backend observa o endereço de origem da conexão para `ip_publico`; o cliente não deve informar um IP público como se fosse confiável.

Se a sede estiver ativa, o backend cria/atualiza a estação, cria uma `sessao_estacao` e retorna um código de lançamento de uso único e curta validade. O PMA abre:

```text
https://<servidor>/station/start?code=<codigo-de-uso-unico>
```

O Angular troca o código por um cookie `HttpOnly` `ORIENTEC_STATION`.

## Usuário

Somente depois da autorização da estação aparece a tela de login.

O login valida:

1. sessão da estação;
2. usuário ativo;
3. senha;
4. sede do usuário = sede da estação;
5. permissões efetivas.

A sessão do usuário recebe dois limites independentes:

- `usuario.tempo_ocioso_minutos`: logout após ausência de interação;
- `usuario.tempo_maximo_sessao_minutos`: limite absoluto desde o login.

Uma requisição periódica não consegue manter uma sessão além do limite absoluto.

## Encerramento

A cada requisição autenticada o backend valida a `sessao_usuario`. Se o limite de inatividade, limite máximo ou sessão da estação for atingido, a API responde `401` com um código de sessão, e o Angular retorna à tela de login.

O PMA pode permanecer aberto durante o expediente sem representar uma sessão de usuário.

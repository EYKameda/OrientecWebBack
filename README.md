# Orientec Backend V3

Backend do Orientec Web: Java 21, Spring Boot 3.5.5, PostgreSQL 17, Flyway, Spring Security, JWT, JPA, MapStruct, idempotência, concorrência e regras configuráveis de OS.

## Segurança de sessão

- estação autorizada independentemente do usuário;
- sessão da estação com heartbeat e TTL configurável;
- código de lançamento de estação de uso único e curta validade;
- login do usuário somente após estação autorizada;
- `usuario.tempo_ocioso_minutos` para logout por inatividade;
- `usuario.tempo_maximo_sessao_minutos` para limite absoluto da sessão;
- backend valida a sessão em toda requisição autenticada;
- frontend não é autoridade de segurança;
- permissões e matriz de transição continuam sendo verificadas no backend.

## Endpoints de estação

- `POST /api/v1/stations/handshake`
- `POST /api/v1/stations/exchange`
- `GET /api/v1/stations/session`
- `POST /api/v1/stations/heartbeat`

## Autenticação

- `POST /api/v1/auth/login`
- `POST /api/v1/auth/logout`

Configure `JWT_SECRET` com uma chave aleatória de pelo menos 32 bytes. O valor de desenvolvimento presente no `application.yml` não deve ser usado em produção.

## Banco

As migrations V1..V10 estão em `src/main/resources/db/migration`. O schema é gerenciado pelo Flyway.

O projeto separado `Orientec-DB-V3` contém as mesmas migrations, Docker Compose do PostgreSQL e cargas SQL.

## Verificação

Nesta entrega o ambiente disponível não possui Maven instalado e a instalação das dependências npm excedeu o tempo de execução disponível; portanto não foi declarado build verde/compilado. Execute localmente:

```bash
mvn clean test
mvn clean package

cd ../Orientec-Frontend
npm install
npm run build
```

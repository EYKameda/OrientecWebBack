-- Condições configuráveis da matriz. Nenhuma condição é criada aqui sem evidência no SISAGE.
CREATE TABLE IF NOT EXISTS regra_condicao_os (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    regra_transicao_id BIGINT NOT NULL,
    campo VARCHAR(80) NOT NULL,
    operador VARCHAR(20) NOT NULL,
    valor VARCHAR(255),
    ordem SMALLINT NOT NULL DEFAULT 1,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_regra_condicao_transicao FOREIGN KEY (regra_transicao_id) REFERENCES regra_transicao_os(id),
    CONSTRAINT ck_regra_condicao_operador CHECK (operador IN ('EQ','NE','GT','GE','LT','LE','IS_TRUE','IS_FALSE','NOT_NULL','NULL'))
);
CREATE INDEX IF NOT EXISTS ix_regra_condicao_regra ON regra_condicao_os(regra_transicao_id,ordem);

-- A matriz continua sendo a fonte única das ações. O motor só executa regras ativas.

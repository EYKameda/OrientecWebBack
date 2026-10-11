-- Regras configuráveis da OS.
-- Situações nunca são excluídas fisicamente; apenas podem ser inativadas.
ALTER TABLE os_status ADD COLUMN IF NOT EXISTS observacao TEXT;

-- Situação 11 foi comprovada no SISAGE como "ENVIADO PARA CREDENCIADO".
INSERT INTO os_status(id,codigo,descricao,ativo,observacao)
VALUES (11,'ENVIADO_CREDENCIADO','ENVIADO PARA CREDENCIADO',TRUE,
        'Descrição comprovada no executável SISAGE.exe; 2061 registros no dump atual.')
ON CONFLICT(id) DO UPDATE SET codigo=EXCLUDED.codigo, descricao=EXCLUDED.descricao,
                              observacao=EXCLUDED.observacao;

ALTER TABLE regra_transicao_os
    ADD COLUMN IF NOT EXISTS tipo_transicao VARCHAR(30) NOT NULL DEFAULT 'NORMAL',
    ADD COLUMN IF NOT EXISTS exige_autorizacao BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN IF NOT EXISTS permissao_execucao VARCHAR(50),
    ADD COLUMN IF NOT EXISTS permissao_autorizacao VARCHAR(50),
    ADD COLUMN IF NOT EXISTS exige_outro_usuario BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN IF NOT EXISTS metodo_autorizacao VARCHAR(30) NOT NULL DEFAULT 'NENHUM';

ALTER TABLE regra_transicao_os
    ADD CONSTRAINT ck_regra_transicao_tipo CHECK (tipo_transicao IN ('NORMAL','RETROCESSO','EXCECAO')),
    ADD CONSTRAINT ck_regra_transicao_metodo CHECK (metodo_autorizacao IN ('NENHUM','SENHA_OUTRO_ACESSO','CHAVE','OUTRO'));

UPDATE regra_transicao_os
SET tipo_transicao = CASE WHEN eh_retrocesso THEN 'RETROCESSO' ELSE 'NORMAL' END,
    exige_autorizacao = eh_retrocesso,
    permissao_execucao = CASE WHEN eh_retrocesso THEN 'OS_RETROCEDER' ELSE NULL END,
    permissao_autorizacao = CASE WHEN eh_retrocesso THEN 'OS_RETROCEDER' ELSE NULL END,
    exige_outro_usuario = eh_retrocesso,
    metodo_autorizacao = CASE WHEN eh_retrocesso THEN 'SENHA_OUTRO_ACESSO' ELSE 'NENHUM' END;

CREATE TABLE IF NOT EXISTS regra_operacao_os (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(60) NOT NULL UNIQUE,
    descricao VARCHAR(150) NOT NULL,
    permissao_execucao VARCHAR(50),
    exige_autorizacao BOOLEAN NOT NULL DEFAULT FALSE,
    permissao_autorizacao VARCHAR(50),
    exige_outro_usuario BOOLEAN NOT NULL DEFAULT FALSE,
    metodo_autorizacao VARCHAR(30) NOT NULL DEFAULT 'NENHUM',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    observacao TEXT,
    criado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_regra_operacao_metodo CHECK (metodo_autorizacao IN ('NENHUM','SENHA_OUTRO_ACESSO','CHAVE','OUTRO'))
);

-- Regras novas solicitadas para o Orientec. Não são apresentadas como regras
-- comprovadas do legado; elas representam o comportamento configurável do novo sistema.
INSERT INTO regra_operacao_os
(codigo,descricao,permissao_execucao,exige_autorizacao,permissao_autorizacao,exige_outro_usuario,metodo_autorizacao,observacao)
VALUES
('ALTERAR_VALOR_ORCAMENTO','Alterar valor do orçamento','OS_ORCAMENTO',TRUE,'OS_AUTORIZAR_EXCEPCIONAL',TRUE,'SENHA_OUTRO_ACESSO','Regra do novo Orientec: alteração de valor exige liberação de outro acesso.'),
('ALTERAR_DESCONTO','Alterar desconto do orçamento','OS_ORCAMENTO',TRUE,'OS_AUTORIZAR_EXCEPCIONAL',TRUE,'SENHA_OUTRO_ACESSO','Regra do novo Orientec: alteração de desconto exige liberação de outro acesso.'),
('ALTERAR_VALOR_PECA','Alterar valor de peça da OS','OS_EXECUTAR',TRUE,'OS_AUTORIZAR_EXCEPCIONAL',TRUE,'SENHA_OUTRO_ACESSO','Regra do novo Orientec: alteração de valor exige liberação de outro acesso.'),
('ALTERAR_VALOR_TOTAL','Alterar valor total da OS','OS_CONTROLAR',TRUE,'OS_AUTORIZAR_EXCEPCIONAL',TRUE,'SENHA_OUTRO_ACESSO','Regra do novo Orientec: alteração de valor exige liberação de outro acesso.')
ON CONFLICT(codigo) DO UPDATE SET
descricao=EXCLUDED.descricao,
permissao_execucao=EXCLUDED.permissao_execucao,
exige_autorizacao=EXCLUDED.exige_autorizacao,
permissao_autorizacao=EXCLUDED.permissao_autorizacao,
exige_outro_usuario=EXCLUDED.exige_outro_usuario,
metodo_autorizacao=EXCLUDED.metodo_autorizacao,
observacao=EXCLUDED.observacao,
ativo=TRUE,
atualizado_em=CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS ordem_servico_liberacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ordem_servico_id BIGINT NOT NULL,
    regra_operacao_id BIGINT NOT NULL,
    usuario_solicitante_codigo BIGINT NOT NULL,
    usuario_autorizador_codigo BIGINT NOT NULL,
    data_hora TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    metodo_autorizacao VARCHAR(30) NOT NULL,
    referencia_chave VARCHAR(120),
    valor_anterior NUMERIC(14,2),
    valor_novo NUMERIC(14,2),
    justificativa TEXT NOT NULL,
    FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id),
    FOREIGN KEY(regra_operacao_id) REFERENCES regra_operacao_os(id),
    FOREIGN KEY(usuario_solicitante_codigo) REFERENCES usuario(codigo),
    FOREIGN KEY(usuario_autorizador_codigo) REFERENCES usuario(codigo),
    CHECK(usuario_solicitante_codigo <> usuario_autorizador_codigo),
    CHECK(length(btrim(justificativa)) > 0)
);
CREATE INDEX IF NOT EXISTS ix_os_liberacao_os_data
    ON ordem_servico_liberacao(ordem_servico_id,data_hora DESC);

-- Regras e liberações são trilha de auditoria/configuração; não usar DELETE físico.

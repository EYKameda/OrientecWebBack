INSERT INTO permissao(codigo,descricao,modulo) VALUES ('OS_COMUNICAR','Enviar comunicações ao cliente','OS') ON CONFLICT(codigo) DO UPDATE SET descricao=EXCLUDED.descricao,modulo=EXCLUDED.modulo;
INSERT INTO usuario_permissao(usuario_codigo,permissao_id) SELECT 1,id FROM permissao WHERE codigo='OS_COMUNICAR' ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS comunicacao (
    id BIGSERIAL PRIMARY KEY,
    ordem_servico_id BIGINT,
    orcamento_id BIGINT,
    cliente_id INTEGER NOT NULL,
    canal VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_hora TIMESTAMPTZ NOT NULL,
    data_hora_envio TIMESTAMPTZ,
    destinatario VARCHAR(200) NOT NULL,
    assunto VARCHAR(200),
    mensagem TEXT NOT NULL,
    usuario_codigo BIGINT NOT NULL,
    erro TEXT,
    CONSTRAINT fk_comunicacao_os FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico(id),
    CONSTRAINT fk_comunicacao_orcamento FOREIGN KEY (orcamento_id) REFERENCES ordem_servico_orcamento(id),
    CONSTRAINT fk_comunicacao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT ck_comunicacao_canal CHECK (canal IN ('WHATSAPP','SMS','EMAIL')),
    CONSTRAINT ck_comunicacao_status CHECK (status IN ('SOLICITADO','ENVIADO','ENTREGUE','FALHA')),
    CONSTRAINT ck_comunicacao_referencia CHECK (ordem_servico_id IS NOT NULL OR orcamento_id IS NOT NULL)
);
CREATE INDEX IF NOT EXISTS ix_comunicacao_os ON comunicacao(ordem_servico_id, data_hora DESC);
CREATE INDEX IF NOT EXISTS ix_comunicacao_orcamento ON comunicacao(orcamento_id, data_hora DESC);

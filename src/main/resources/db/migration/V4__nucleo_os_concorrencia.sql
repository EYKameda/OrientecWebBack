ALTER TABLE ordem_servico
    ADD COLUMN IF NOT EXISTS version BIGINT NOT NULL DEFAULT 0;

CREATE INDEX IF NOT EXISTS ix_ordem_servico_version
    ON ordem_servico (id, version);

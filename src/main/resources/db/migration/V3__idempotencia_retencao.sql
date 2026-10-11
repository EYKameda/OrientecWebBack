-- Retenção operacional inicial.
-- A limpeza automática deve ser feita por job controlado, nunca
-- durante a transação de negócio.
CREATE INDEX IF NOT EXISTS ix_idempotency_key_updated_at
    ON idempotency_key (updated_at);

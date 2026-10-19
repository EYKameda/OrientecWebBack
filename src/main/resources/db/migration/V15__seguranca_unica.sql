-- Configuração única de segurança. A senha inicial é convertida para BCrypt no startup.
CREATE TABLE IF NOT EXISTS seguranca (
    id SMALLINT PRIMARY KEY CHECK (id = 1),
    login VARCHAR(80) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    orientec_dev_mode BOOLEAN NOT NULL DEFAULT TRUE,
    atualizado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_seguranca_login UNIQUE (login)
);

INSERT INTO seguranca (id, login, senha, orientec_dev_mode)
VALUES (1, 'admin', '!INICIALIZAR_BCRYPT_ADMIN!', TRUE)
ON CONFLICT (id) DO NOTHING;

-- As tabelas anteriores foram substituídas pela tabela única Seguranca.
DROP TABLE IF EXISTS administrador_configuracao;
DROP TABLE IF EXISTS configuracao_seguranca;

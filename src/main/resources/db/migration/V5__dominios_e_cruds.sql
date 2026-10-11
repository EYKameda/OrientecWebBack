
-- Domínios/cadastros necessários ao CRUD V3. Não remove nem altera dados legados.
CREATE TABLE IF NOT EXISTS origem_os (
 id SMALLINT PRIMARY KEY, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS tipo_garantia (
 id SMALLINT PRIMARY KEY, codigo VARCHAR(30) NOT NULL UNIQUE, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS tipo_pendencia (
 id SMALLINT PRIMARY KEY, codigo VARCHAR(30) NOT NULL UNIQUE, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS defeito (
 id SMALLINT PRIMARY KEY, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS acessorio (
 id SMALLINT PRIMARY KEY, descricao VARCHAR(150) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS familia (
 id INTEGER PRIMARY KEY, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS marca (
 id INTEGER PRIMARY KEY, descricao VARCHAR(100) NOT NULL UNIQUE, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS linha (
 id INTEGER PRIMARY KEY, descricao VARCHAR(100) NOT NULL, ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS modelo (
 id INTEGER PRIMARY KEY, marca_id INTEGER NOT NULL, linha_id INTEGER, descricao VARCHAR(150) NOT NULL,
 complemento VARCHAR(100), ativo BOOLEAN NOT NULL DEFAULT TRUE,
 FOREIGN KEY(marca_id) REFERENCES marca(id), FOREIGN KEY(linha_id) REFERENCES linha(id)
);
CREATE TABLE IF NOT EXISTS cliente (
 id INTEGER PRIMARY KEY, nome VARCHAR(150) NOT NULL, cpf_cnpj VARCHAR(30), telefone VARCHAR(30), celular VARCHAR(30),
 email VARCHAR(150), endereco VARCHAR(255), numero VARCHAR(20), complemento VARCHAR(100), bairro VARCHAR(100),
 cidade VARCHAR(100), uf CHAR(2), cep VARCHAR(10), ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS fornecedor (
 id INTEGER PRIMARY KEY, nome VARCHAR(150) NOT NULL, fantasia VARCHAR(150), cnpj VARCHAR(30), cpf VARCHAR(30), ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS credenciado (
 id INTEGER PRIMARY KEY, nome VARCHAR(150) NOT NULL, fantasia VARCHAR(150), cnpj VARCHAR(30), cpf VARCHAR(30), ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS item (
 id INTEGER PRIMARY KEY, descricao VARCHAR(150), aplicacao TEXT, codigo_barras VARCHAR(30), familia_id INTEGER,
 ativo BOOLEAN NOT NULL DEFAULT TRUE, FOREIGN KEY(familia_id) REFERENCES familia(id)
);
CREATE TABLE IF NOT EXISTS pedido (
 id INTEGER PRIMARY KEY, fornecedor_id INTEGER, data_pedido DATE, situacao VARCHAR(30), observacao TEXT,
 ativo BOOLEAN NOT NULL DEFAULT TRUE, FOREIGN KEY(fornecedor_id) REFERENCES fornecedor(id)
);
CREATE TABLE IF NOT EXISTS ordem_servico_pendencia (
 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, ordem_servico_id BIGINT NOT NULL, tipo_pendencia_id SMALLINT NOT NULL,
 descricao TEXT, data_inclusao TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP, data_resolucao TIMESTAMPTZ,
 resolvida BOOLEAN NOT NULL DEFAULT FALSE, usuario_codigo BIGINT,
 FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id), FOREIGN KEY(tipo_pendencia_id) REFERENCES tipo_pendencia(id),
 FOREIGN KEY(usuario_codigo) REFERENCES usuario(codigo)
);
CREATE TABLE IF NOT EXISTS ordem_servico_peca (
 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, ordem_servico_id BIGINT NOT NULL, sequencia INTEGER, item_id INTEGER,
 descricao_snapshot VARCHAR(150), fornecedor_id INTEGER, pedido_id INTEGER, preco_unitario NUMERIC(14,2), preco_venda NUMERIC(14,2),
 quantidade NUMERIC(14,3), total NUMERIC(14,2), FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id),
 FOREIGN KEY(item_id) REFERENCES item(id), FOREIGN KEY(fornecedor_id) REFERENCES fornecedor(id), FOREIGN KEY(pedido_id) REFERENCES pedido(id)
);
CREATE TABLE IF NOT EXISTS ordem_servico_orcamento (
 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, ordem_servico_id BIGINT NOT NULL, valor_pecas NUMERIC(14,2),
 valor_servico NUMERIC(14,2), desconto NUMERIC(14,2), valor_total NUMERIC(14,2), data_orcamento TIMESTAMPTZ,
 usuario_codigo BIGINT, observacao TEXT, FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id), FOREIGN KEY(usuario_codigo) REFERENCES usuario(codigo)
);
CREATE TABLE IF NOT EXISTS ordem_servico_defeito (
 ordem_servico_id BIGINT NOT NULL, defeito_id SMALLINT NOT NULL, PRIMARY KEY(ordem_servico_id, defeito_id),
 FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id), FOREIGN KEY(defeito_id) REFERENCES defeito(id)
);
CREATE TABLE IF NOT EXISTS ordem_servico_acessorio (
 ordem_servico_id BIGINT NOT NULL, acessorio_id SMALLINT NOT NULL, PRIMARY KEY(ordem_servico_id, acessorio_id),
 FOREIGN KEY(ordem_servico_id) REFERENCES ordem_servico(id), FOREIGN KEY(acessorio_id) REFERENCES acessorio(id)
);
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS cliente_id INTEGER;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS equipamento_modelo_id INTEGER;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS origem_id SMALLINT;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS tipo_garantia_id SMALLINT;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS defeito_relatado TEXT;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS observacao TEXT;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS data_abertura DATE;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS hora_abertura TIME;
ALTER TABLE ordem_servico ADD COLUMN IF NOT EXISTS atualizado_em_legacy TIMESTAMPTZ;
CREATE INDEX IF NOT EXISTS idx_os_cliente ON ordem_servico(cliente_id);
CREATE INDEX IF NOT EXISTS idx_os_modelo ON ordem_servico(equipamento_modelo_id);
CREATE INDEX IF NOT EXISTS idx_os_origem ON ordem_servico(origem_id);
CREATE INDEX IF NOT EXISTS idx_os_data_abertura ON ordem_servico(data_abertura);
ALTER TABLE ordem_servico ADD CONSTRAINT fk_os_cliente_v5 FOREIGN KEY(cliente_id) REFERENCES cliente(id);
ALTER TABLE ordem_servico ADD CONSTRAINT fk_os_modelo_v5 FOREIGN KEY(equipamento_modelo_id) REFERENCES modelo(id);
ALTER TABLE ordem_servico ADD CONSTRAINT fk_os_origem_v5 FOREIGN KEY(origem_id) REFERENCES origem_os(id);
ALTER TABLE ordem_servico ADD CONSTRAINT fk_os_garantia_v5 FOREIGN KEY(tipo_garantia_id) REFERENCES tipo_garantia(id);

-- O registro técnico inicial usa codigo=1; reposiciona a sequência para evitar colisão no próximo usuário.
SELECT setval(pg_get_serial_sequence('usuario','codigo'), GREATEST(COALESCE((SELECT MAX(codigo) FROM usuario),0)+1,1), false);

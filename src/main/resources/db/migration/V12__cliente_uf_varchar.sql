-- Mantém o mapeamento JPA de Cliente.uf alinhado ao tipo varchar(2).
-- A tabela cliente pode ser legada e externa às migrations iniciais do Orientec.
DO $$
BEGIN
    IF to_regclass('public.cliente') IS NOT NULL THEN
        ALTER TABLE public.cliente
            ALTER COLUMN uf TYPE VARCHAR(2);
    END IF;
END
$$;

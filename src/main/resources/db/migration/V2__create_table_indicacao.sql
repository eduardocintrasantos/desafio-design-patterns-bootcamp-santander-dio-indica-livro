CREATE TABLE indicacao (
    id           BIGSERIAL PRIMARY KEY,
    livro_id     BIGINT NOT NULL REFERENCES livro(id),
    nome         VARCHAR(100) NOT NULL,
    nota         VARCHAR(20)  NOT NULL,
    motivo       TEXT         NOT NULL,
    criado_em    TIMESTAMP DEFAULT NOW()
);
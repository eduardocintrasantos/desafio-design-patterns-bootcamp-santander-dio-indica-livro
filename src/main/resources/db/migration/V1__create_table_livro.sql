CREATE TABLE livro (
    id                   BIGSERIAL PRIMARY KEY,
    google_volume_id     VARCHAR(100),
    livro_titulo         VARCHAR(255),
    livro_autor          VARCHAR(255),
    livro_img            VARCHAR(500)
);
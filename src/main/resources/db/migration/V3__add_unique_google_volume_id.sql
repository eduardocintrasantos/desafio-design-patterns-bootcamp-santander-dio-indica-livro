ALTER TABLE livro
    ALTER COLUMN google_volume_id SET NOT NULL;

ALTER TABLE livro
    ADD CONSTRAINT uk_livro_google_volume_id UNIQUE (google_volume_id);

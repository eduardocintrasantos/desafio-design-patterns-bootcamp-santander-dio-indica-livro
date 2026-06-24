package org.example.desafiodesignpatternsbootcampsantanderdio.model;

import jakarta.persistence.*;

/**
 * @Entity = classe mapeada para tabela do banco.
 * Livro é persistido UMA vez; várias indicações apontam para ele.
 */
@Entity
@Table(name = "livro")

public class Livro {

    /** id interno do banco (BIGSERIAL) — PK da nossa aplicação */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID vindo da Google Books (volumeId).
     * UNIQUE garante que o mesmo livro não seja cadastrado duas vezes.
     */
    @Column(name = "google_volume_id", nullable = false, unique = true, length = 100)
    private String googleVolumeId;

    @Column(name = "livro_titulo", nullable = false)
    private String titulo;

    @Column(name = "livro_autor")
    private String autor;

    @Column(name = "livro_img", length = 500)
    private String img;

    public Livro() {
    }

    public Livro(String googleVolumeId, String titulo, String autor, String img) {
        this.googleVolumeId = googleVolumeId;
        this.titulo = titulo;
        this.autor = autor;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoogleVolumeId() {
        return googleVolumeId;
    }

    public void setGoogleVolumeId(String googleVolumeId) {
        this.googleVolumeId = googleVolumeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

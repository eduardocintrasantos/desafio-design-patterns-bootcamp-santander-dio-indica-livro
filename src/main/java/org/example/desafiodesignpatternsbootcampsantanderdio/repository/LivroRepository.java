package org.example.desafiodesignpatternsbootcampsantanderdio.repository;

import org.example.desafiodesignpatternsbootcampsantanderdio.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByGoogleVolumeId(String googleVolumeId);
}

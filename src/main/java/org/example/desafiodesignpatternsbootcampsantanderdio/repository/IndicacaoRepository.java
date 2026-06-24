package org.example.desafiodesignpatternsbootcampsantanderdio.repository;

import org.example.desafiodesignpatternsbootcampsantanderdio.model.Indicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndicacaoRepository extends JpaRepository<Indicacao, Long> {
    List<Indicacao> findByLivro_Id(Long livroId);
}

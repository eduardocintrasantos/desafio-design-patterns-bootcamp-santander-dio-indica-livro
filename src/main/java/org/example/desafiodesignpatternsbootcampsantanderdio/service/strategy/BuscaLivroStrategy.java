package org.example.desafiodesignpatternsbootcampsantanderdio.service.strategy;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;

import java.util.List;

public interface BuscaLivroStrategy {
    List<LivroResumoDTO> buscar(String termo);
}

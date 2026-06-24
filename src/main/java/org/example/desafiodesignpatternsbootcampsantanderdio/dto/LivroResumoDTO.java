package org.example.desafiodesignpatternsbootcampsantanderdio.dto;

/**
 * O que o CLIENTE vê ao buscar livros.
 * googleVolumeId = cliente usa no POST /indicacoes para identificar o livro.
 */
public record LivroResumoDTO(
        String googleVolumeId,
        String titulo,
        String autor,
        String img
) {
}

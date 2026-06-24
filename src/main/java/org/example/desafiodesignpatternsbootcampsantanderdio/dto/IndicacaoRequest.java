package org.example.desafiodesignpatternsbootcampsantanderdio.dto;

public record IndicacaoRequest(
        String googleVolumeId,
        String titulo,   // snapshot se livro for criado agora
        String autor,
        String img,
        String nome,
        int nota,        // 1 a 5 — convertemos para enum no service
        String motivo
) {
}

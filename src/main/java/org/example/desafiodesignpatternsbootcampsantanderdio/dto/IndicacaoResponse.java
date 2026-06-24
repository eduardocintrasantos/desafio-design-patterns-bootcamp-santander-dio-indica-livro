package org.example.desafiodesignpatternsbootcampsantanderdio.dto;

import org.example.desafiodesignpatternsbootcampsantanderdio.model.Nota;

import java.time.LocalDateTime;

public record IndicacaoResponse(
        Long id,
        Long livroId,
        String googleVolumeId,
        String titulo,
        String autor,
        String img,
        String nome,
        Nota nota,
        String motivo,
        LocalDateTime criadoEm
) {
}

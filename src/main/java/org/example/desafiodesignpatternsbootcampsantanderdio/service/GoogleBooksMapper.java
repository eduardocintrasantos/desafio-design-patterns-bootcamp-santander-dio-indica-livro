package org.example.desafiodesignpatternsbootcampsantanderdio.service;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.GoogleBooksResponse;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;
import org.example.desafiodesignpatternsbootcampsantanderdio.model.Livro;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GoogleBooksMapper {

    public List<LivroResumoDTO> toResumoList(GoogleBooksResponse response) {
        if (response == null || response.getItems() == null) {
            return Collections.emptyList();
        }

        return response.getItems().stream()
                .map(this::toResumo)
                .toList();
    }

    public LivroResumoDTO toResumo(GoogleBooksResponse.Item item) {
        var info = item.getVolumeInfo();
        String autor = (info.getAuthors() != null && !info.getAuthors().isEmpty())
                ? String.join(", ", info.getAuthors())
                : "Autor desconhecido";

        String img = info.getImageLinks() != null ? info.getImageLinks().getThumbnail() : null;

        return new LivroResumoDTO(
                item.getId(),
                info.getTitle(),
                autor,
                img
        );
    }

    public Livro toEntity(LivroResumoDTO dto) {
        return new Livro(
                dto.googleVolumeId(),
                dto.titulo(),
                dto.autor(),
                dto.img()
        );
    }
}

package org.example.desafiodesignpatternsbootcampsantanderdio.service.strategy;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.GoogleBooksMapper;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.LivroAutorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaPorAutorStrategy implements BuscaLivroStrategy {

    private final LivroAutorService livroAutorService;
    private final GoogleBooksMapper mapper;

    public BuscaPorAutorStrategy(LivroAutorService livroAutorService, GoogleBooksMapper mapper) {
        this.livroAutorService = livroAutorService;
        this.mapper = mapper;
    }

    @Override
    public List<LivroResumoDTO> buscar(String termo) {
        return mapper.toResumoList(livroAutorService.buscar("inauthor:" + termo));
    }
}

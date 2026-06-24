package org.example.desafiodesignpatternsbootcampsantanderdio.service.strategy;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.GoogleBooksMapper;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaPorTituloStrategy implements BuscaLivroStrategy {

    private final LivroService livroService;
    private final GoogleBooksMapper mapper;

    public BuscaPorTituloStrategy(LivroService livroService, GoogleBooksMapper mapper) {
        this.livroService = livroService;
        this.mapper = mapper;
    }

    @Override
    public List<LivroResumoDTO> buscar(String termo) {
        return mapper.toResumoList(livroService.buscar("intitle:" + termo));
    }
}

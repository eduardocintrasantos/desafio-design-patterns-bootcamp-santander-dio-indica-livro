package org.example.desafiodesignpatternsbootcampsantanderdio.controller;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.strategy.BuscaPorAutorStrategy;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.strategy.BuscaPorTituloStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroRestController {

    private final BuscaPorTituloStrategy buscaPorTitulo;
    private final BuscaPorAutorStrategy buscaPorAutor;

    public LivroRestController(
            BuscaPorTituloStrategy buscaPorTitulo,
            BuscaPorAutorStrategy buscaPorAutor
    ) {
        this.buscaPorTitulo = buscaPorTitulo;
        this.buscaPorAutor = buscaPorAutor;
    }

    @GetMapping
    public ResponseEntity<List<LivroResumoDTO>> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor
    ) {
        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(buscaPorTitulo.buscar(titulo));
        }
        if (autor != null && !autor.isBlank()) {
            return ResponseEntity.ok(buscaPorAutor.buscar(autor));
        }
        return ResponseEntity.badRequest().build();
    }
}

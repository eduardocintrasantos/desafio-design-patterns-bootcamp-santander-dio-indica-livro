package org.example.desafiodesignpatternsbootcampsantanderdio.service;

import org.example.desafiodesignpatternsbootcampsantanderdio.config.GoogleBooksFeignConfig;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.GoogleBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign = cliente HTTP declarativo — Spring gera a implementação em runtime.
 *
 * name = identificador único do client (não pode repetir entre Feign clients).
 * url  = base da API; path e query vão nos métodos.
 *
 * q=intitle:xxx = sintaxe de busca da Google Books por título.
 */
@FeignClient(name = "google-books-titulo", url = "https://www.googleapis.com/books/v1", configuration = GoogleBooksFeignConfig.class)
public interface LivroService {
    @GetMapping("/volumes")
    GoogleBooksResponse buscar(@RequestParam("q") String query);
}

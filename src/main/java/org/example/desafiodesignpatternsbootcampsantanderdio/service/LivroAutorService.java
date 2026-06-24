package org.example.desafiodesignpatternsbootcampsantanderdio.service;

import org.example.desafiodesignpatternsbootcampsantanderdio.config.GoogleBooksFeignConfig;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.GoogleBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "google-books-autor", url = "https://www.googleapis.com/books/v1", configuration = GoogleBooksFeignConfig.class)
public interface LivroAutorService {
    @GetMapping("/volumes")
    GoogleBooksResponse buscar(@RequestParam("q") String query);
}

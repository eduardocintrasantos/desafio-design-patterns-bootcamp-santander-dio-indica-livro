package org.example.desafiodesignpatternsbootcampsantanderdio.config;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(FeignException.TooManyRequests.class)
    public ResponseEntity<Map<String, String>> handleGoogleBooksQuota(FeignException.TooManyRequests ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
                "mensagem",
                "Limite diário da Google Books atingido. Configure google.books.api-key no application.properties ou tente novamente amanhã."
        ));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeign(FeignException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of(
                "mensagem",
                "Erro ao consultar Google Books (HTTP " + ex.status() + ")."
        ));
    }
}

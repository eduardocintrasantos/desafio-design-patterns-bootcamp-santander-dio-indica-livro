package org.example.desafiodesignpatternsbootcampsantanderdio.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleBooksFeignConfig {

    @Bean
    public RequestInterceptor googleBooksApiKeyInterceptor(
            @Value("${google.books.api-key:}") String apiKey
    ) {
        return template -> {
            if (apiKey != null && !apiKey.isBlank()) {
                template.query("key", apiKey.trim());
            }
        };
    }
}

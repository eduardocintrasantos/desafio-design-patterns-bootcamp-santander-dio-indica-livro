package org.example.desafiodesignpatternsbootcampsantanderdio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioDesignPatternsBootcampSantanderDioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioDesignPatternsBootcampSantanderDioApplication.class, args);
    }

}

package org.example.desafiodesignpatternsbootcampsantanderdio.controller;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.IndicacaoRequest;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.IndicacaoResponse;
import org.example.desafiodesignpatternsbootcampsantanderdio.service.IndicacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/indicacoes")
public class IndicacaoRestController {

    private final IndicacaoService indicacaoService;

    public IndicacaoRestController(IndicacaoService indicacaoService) {
        this.indicacaoService = indicacaoService;
    }

    @PostMapping
    public ResponseEntity<IndicacaoResponse> cadastrar(@RequestBody IndicacaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(indicacaoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<IndicacaoResponse>> listar() {
        return ResponseEntity.ok(indicacaoService.listarTodas());
    }
}

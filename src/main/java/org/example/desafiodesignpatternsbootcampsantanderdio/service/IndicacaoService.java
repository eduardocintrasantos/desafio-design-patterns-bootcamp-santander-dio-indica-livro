package org.example.desafiodesignpatternsbootcampsantanderdio.service;

import org.example.desafiodesignpatternsbootcampsantanderdio.dto.IndicacaoRequest;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.IndicacaoResponse;
import org.example.desafiodesignpatternsbootcampsantanderdio.dto.LivroResumoDTO;
import org.example.desafiodesignpatternsbootcampsantanderdio.model.Indicacao;
import org.example.desafiodesignpatternsbootcampsantanderdio.model.Livro;
import org.example.desafiodesignpatternsbootcampsantanderdio.model.Nota;
import org.example.desafiodesignpatternsbootcampsantanderdio.repository.IndicacaoRepository;
import org.example.desafiodesignpatternsbootcampsantanderdio.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndicacaoService {

    private final LivroRepository livroRepository;
    private final IndicacaoRepository indicacaoRepository;
    private final GoogleBooksMapper mapper;

    public IndicacaoService(
            LivroRepository livroRepository,
            IndicacaoRepository indicacaoRepository,
            GoogleBooksMapper mapper
    ) {
        this.livroRepository = livroRepository;
        this.indicacaoRepository = indicacaoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public IndicacaoResponse cadastrar(IndicacaoRequest request) {
        Livro livro = livroRepository.findByGoogleVolumeId(request.googleVolumeId())
                .orElseGet(() -> livroRepository.save(mapper.toEntity(new LivroResumoDTO(
                        request.googleVolumeId(),
                        request.titulo(),
                        request.autor(),
                        request.img()
                ))));

        Indicacao indicacao = new Indicacao();
        indicacao.setLivro(livro);
        indicacao.setNome(request.nome());
        indicacao.setNota(Nota.fromValor(request.nota()));
        indicacao.setMotivo(request.motivo());

        return toResponse(indicacaoRepository.save(indicacao));
    }

    @Transactional(readOnly = true)
    public List<IndicacaoResponse> listarTodas() {
        return indicacaoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private IndicacaoResponse toResponse(Indicacao indicacao) {
        Livro livro = indicacao.getLivro();
        return new IndicacaoResponse(
                indicacao.getId(),
                livro.getId(),
                livro.getGoogleVolumeId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getImg(),
                indicacao.getNome(),
                indicacao.getNota(),
                indicacao.getMotivo(),
                indicacao.getCriadoEm()
        );
    }
}

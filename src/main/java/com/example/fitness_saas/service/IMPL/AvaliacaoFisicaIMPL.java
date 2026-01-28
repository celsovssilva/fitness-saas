package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.dto.AvaliacaoFisicaDTO;
import com.example.fitness_saas.entity.AvaliacaoFisica;
import com.example.fitness_saas.repository.AlunoRepository;
import com.example.fitness_saas.repository.AvaliacaoFisicaRepository;
import com.example.fitness_saas.repository.PersonalRepository;
import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.service.AvaliacaoFisicaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public  class AvaliacaoFisicaIMPL implements AvaliacaoFisicaService {
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Override
    public AvaliacaoFisicaResponse cadastrar(AvaliacaoFisicaDTO request) {
        AvaliacaoFisica avaliacao = new AvaliacaoFisica();

        avaliacao.setAluno(alunoRepository.findById(request.alunoId()).orElseThrow());
        avaliacao.setPersonal(personalRepository.findById(request.personalId()).orElseThrow());
        avaliacao.setDataAvaliacao(LocalDate.now());
        avaliacao.setPeso(request.peso());
        avaliacao.setAltura(request.altura());
        avaliacao.setPercentualGordura(request.percentualGordura());
        avaliacao.setMassaMuscular(request.massaMuscular());
        avaliacao.setCintura(request.cintura());
        avaliacao.setTorax(request.torax());
        avaliacao.setBracoDireito(request.bracoDireito());
        avaliacao.setCoxaDireita(request.coxaDireita());

        AvaliacaoFisica salva = avaliacaoFisicaRepository.save(avaliacao);
        return converterParaResponse(salva);
    }

    @Override
    public List<AvaliacaoFisicaResponse> buscarHistoricoPorAluno(Long alunoId) {
        return avaliacaoFisicaRepository.findByAlunoIdOrderByDataAvaliacaoDesc(alunoId)
                .stream().map(this::converterParaResponse).toList();
    }

    @Override
    public AvaliacaoFisicaResponse buscarUltimaAvaliacao(Long alunoId) {
        AvaliacaoFisica ultima = avaliacaoFisicaRepository.findTop2ByAlunoIdOrderByDataAvaliacaoDesc(alunoId)
                .stream().findFirst().orElseThrow(() -> new RuntimeException("Nenhuma avaliação encontrada"));
        return converterParaResponse(ultima);
    }

    @Override
    public void deletar(Long id) {
        avaliacaoFisicaRepository.deleteById(id);
    }

    @Override
    public Map<String,Double> compararEvolucao(Long alunoId) {
        List<AvaliacaoFisica> avaliacoes = avaliacaoFisicaRepository.findTop2ByAlunoIdOrderByDataAvaliacaoDesc(alunoId);

        if (avaliacoes.size() < 2) {
            throw new RuntimeException("Dados insuficientes para comparar. São necessárias 2 avaliações.");
        }

        AvaliacaoFisica atual = avaliacoes.get(0);
        AvaliacaoFisica antiga = avaliacoes.get(1);

        Map<String, Double> evolucao = new HashMap<>();
        evolucao.put("difPeso", atual.getPeso() - antiga.getPeso());
        evolucao.put("difGordura", atual.getPercentualGordura() - antiga.getPercentualGordura());
        evolucao.put("difMassaMuscular", atual.getMassaMuscular() - antiga.getMassaMuscular());
        evolucao.put("difCintura", atual.getCintura() - antiga.getCintura());
        evolucao.put("difTorax", atual.getTorax() - antiga.getTorax());
        evolucao.put("difBracoDireito", atual.getBracoDireito() - antiga.getBracoDireito());
        evolucao.put("difCoxaDireita", atual.getCoxaDireita() - antiga.getCoxaDireita());

        return evolucao;
    }
    private AvaliacaoFisicaResponse converterParaResponse(AvaliacaoFisica ent) {
        Double imc = ent.getPeso() / (ent.getAltura() * ent.getAltura());
        return new AvaliacaoFisicaResponse(
                ent.getId(),
                ent.getDataAvaliacao(),
                ent.getAluno().getUser().getName(),
                ent.getPersonal().getUser().getName(),
                ent.getPeso(),
                ent.getAltura(),
                imc,
                ent.getPercentualGordura(),
                ent.getMassaMuscular(),
                ent.getTorax(),
                ent.getCintura(),
                ent.getQuadril(),
                ent.getBracoEsquerdo(),
                ent.getBracoDireito(),
                ent.getCoxaEsquerda(),
                ent.getQuadril());
    }
}
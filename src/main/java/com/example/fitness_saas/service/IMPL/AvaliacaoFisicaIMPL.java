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


        var aluno = alunoRepository.findById(request.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        var personal = personalRepository.findById(request.personalId())
                .orElseThrow(() -> new RuntimeException("Personal não encontrado"));

        avaliacao.setAluno(aluno);
        avaliacao.setPersonal(personal);
        avaliacao.setDataAvaliacao(LocalDate.now());


        avaliacao.setPeso(request.peso());
        avaliacao.setAltura(request.altura());
        avaliacao.setMassaMuscular(request.massaMuscular());
        avaliacao.setCintura(request.cintura());
        avaliacao.setTorax(request.torax());
        avaliacao.setQuadril(request.quadril());
        avaliacao.setBracoDireito(request.bracoDireito());
        avaliacao.setBracoEsquerdo(request.bracoEsquerdo());
        avaliacao.setCoxaDireita(request.coxaDireita());
        avaliacao.setCoxaEsquerda(request.coxaEsquerda());


        avaliacao.setDobraSubescapular(request.dobraSubescapular());
        avaliacao.setDobraTriceps(request.dobraTriceps());
        avaliacao.setDobraPeitoral(request.dobraPeitoral());
        avaliacao.setDobraAxilarMedia(request.dobraAxilarMedia());
        avaliacao.setDobraSupraIliaca(request.dobraSupraIliaca());
        avaliacao.setDobraAbdominal(request.dobraAbdominal());
        avaliacao.setDobraCoxa(request.dobraCoxa());


        if (aluno.getUser().getDataNascimento() == null) {
            throw new RuntimeException("Data de nascimento do aluno é obrigatória para o cálculo.");
        }

        int idade = java.time.Period.between(aluno.getUser().getDataNascimento(), LocalDate.now()).getYears();

        double soma = request.dobraSubescapular() + request.dobraTriceps() + request.dobraPeitoral() +
                request.dobraAxilarMedia() + request.dobraSupraIliaca() +
                request.dobraAbdominal() + request.dobraCoxa();


        double dc = 1.112 - (0.00043499 * soma) + (0.00000055 * Math.pow(soma, 2)) - (0.00028826 * idade);
        double percentualGordura = ((4.95 / dc) - 4.50) * 100;

        avaliacao.setPercentualGordura(Math.round(percentualGordura * 100.0) / 100.0);


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

        Double peso = (ent.getPeso() != null) ? ent.getPeso() : 0.0;
        Double altura = (ent.getAltura() != null && ent.getAltura() > 0) ? ent.getAltura() : 1.0;
        Double percentual = (ent.getPercentualGordura() != null) ? ent.getPercentualGordura() : 0.0;


        Double imc = peso / (altura * altura);
        Double massaGordaKg = peso * (percentual / 100);
        Double massaMagraKg = peso - massaGordaKg;

        return new AvaliacaoFisicaResponse(
                ent.getId(),
                ent.getDataAvaliacao(),
                ent.getAluno().getUser().getName(),
                ent.getPersonal().getUser().getName(),
                peso,
                ent.getAltura(),
                Math.round(imc * 100.0) / 100.0,
                ent.getPercentualGordura(),
                ent.getMassaMuscular(),
                ent.getTorax(),
                ent.getCintura(),
                ent.getQuadril(),
                ent.getBracoEsquerdo(),
                ent.getBracoDireito(),
                ent.getCoxaEsquerda(),
                ent.getCoxaDireita(),
                Math.round(massaGordaKg * 100.0) / 100.0,
                Math.round(massaMagraKg * 100.0) / 100.0
        );
    }
}
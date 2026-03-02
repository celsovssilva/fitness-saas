    package com.example.fitness_saas.response;

    import com.example.fitness_saas.dto.PersonalDTO;
    import com.example.fitness_saas.entity.Aluno;
    import com.example.fitness_saas.entity.AvaliacaoFisica;

    import java.time.LocalDate;

    public record AvaliacaoFisicaResponse(
            Long id,
            LocalDate dataAvaliacao,
            String nomeAluno,
            String nomePersonal,
            Double peso,
            Double altura,
            Double imc,
            Double rcq,
            Double percentualGordura,
            Double massaGordaKg,
            Double massaMagraKg,


            Double torax, Double cintura, Double quadril,
            Double bracoEsquerdo, Double bracoDireito,
            Double coxaEsquerda, Double coxaDireita
    ) {
        public AvaliacaoFisicaResponse(AvaliacaoFisica a ) {
            this(
                    a.getId(),
                    a.getDataAvaliacao(),
                    a.getAluno().getUser().getName(),
                    a.getPersonal().getUser().getName(),
                    a.getPeso(),
                    a.getAltura(),

                    (a.getPeso() != null && a.getAltura() != null) ? Math.round((a.getPeso() / (a.getAltura() * a.getAltura())) * 100.0) / 100.0 : 0.0,

                    (a.getCintura() != null && a.getQuadril() != null && a.getQuadril() > 0) ? Math.round((a.getCintura() / a.getQuadril()) * 100.0) / 100.0 : 0.0,
                    a.getPercentualGordura(),

                    (a.getPeso() != null && a.getPercentualGordura() != null) ? Math.round((a.getPeso() * (a.getPercentualGordura() / 100)) * 100.0) / 100.0 : 0.0,

                    (a.getPeso() != null && a.getPercentualGordura() != null) ? Math.round((a.getPeso() - (a.getPeso() * (a.getPercentualGordura() / 100))) * 100.0) / 100.0 : 0.0,
                    a.getTorax(), a.getCintura(), a.getQuadril(),
                    a.getBracoEsquerdo(), a.getBracoDireito(),
                    a.getCoxaEsquerda(), a.getCoxaDireita()
            );
        }
        }


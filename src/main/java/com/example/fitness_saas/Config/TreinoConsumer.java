package com.example.fitness_saas.Config;

import com.example.fitness_saas.dto.TreinoEmailDTO;
import com.example.fitness_saas.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;

public class TreinoConsumer {
    @Autowired
    EmailService emailService;

    @RabbitListener(queues = RabbiMQConfig.QUEUE_TREINO)
    public void consumirNotificacaoTreino(TreinoEmailDTO dto){
        String subject = "\uD83D\uDE80 Novo Treino Disponível!";
        String corpo = "Olá " + dto.nomeAluno() + " acabou de atualizar sua ficha.\n" +
                "O treino de hoje é: " + dto.tipoTreino() + ".\n\n" +
                "Bora treinar? Acesse o app e confira os exercícios!";
        emailService.enviarEmail(dto.nomeAluno(), dto.tipoTreino(), corpo);
        System.out.println("Treino enviado com sucesso!");

    }
}

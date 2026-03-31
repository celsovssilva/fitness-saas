package com.example.fitness_saas.Config;

import com.example.fitness_saas.dto.TreinoEmailDTO;
import com.example.fitness_saas.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreinoConsumer {
    @Autowired
    EmailService emailService;

    @RabbitListener(queues = RabbiMQConfig.QUEUE_TREINO)
    public void consumirNotificacaoTreino(TreinoEmailDTO dto){


        String subject = "🚀 Novo Treino Disponível!";


        String corpo = "Olá, " + dto.nomeAluno() + "!\n\n" +
                "Seu personal " + dto.nomePersonal() + " acabou de atualizar sua ficha.\n" +
                "O treino de hoje é: " + dto.tipoTreino() + ".\n\n" +
                "Bora treinar? Acesse o app e confira os exercícios!";


        emailService.enviarEmail(dto.emailAluno(), subject, corpo);

        System.out.println("E-mail de treino enviado com sucesso para: " + dto.emailAluno());
    }
}

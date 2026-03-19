package com.example.fitness_saas.Config;

import com.example.fitness_saas.Config.RabbiMQConfig;
import com.example.fitness_saas.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;
    @RabbitListener(queues = RabbiMQConfig.QUEUE_NAME)
    public void consume(String message) {
        System.out.println("LOG [CONSUMER]: Recebi um pedido da fila: " + message);

        try {
            String[] partes = message.split("\\|");
            String emailDestino = partes[0].replace("enviar email para ", "").trim();
            String linkReset = partes[1].replace(" link: ", "").trim();


            String assunto = "Recuperação de Senha - Fitness SaaS";
            String corpo = "Olá! Clique no link para definir sua nova senha: " + linkReset;

            emailService.enviarEmail(emailDestino, assunto, corpo);

            System.out.println("LOG [CONSUMER]: E-mail enviado com sucesso para " + emailDestino);

        } catch (Exception e) {
            System.err.println("ERRO [CONSUMER]: Falha ao processar mensagem: " + e.getMessage());

        }
    }
}
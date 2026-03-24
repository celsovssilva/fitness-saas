package com.example.fitness_saas.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbiMQConfig {
    public static final String QUEUE_EMAIL = "email.queue";
    public static final String QUEUE_TREINO = "treino.queue";

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_EMAIL, true);
    }

    @Bean
    public Queue treinoQueue() {
        return new Queue(QUEUE_TREINO, true);
    }
}

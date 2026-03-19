package com.example.fitness_saas.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbiMQConfig {
    public static final String QUEUE_NAME = "email.queue";

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}

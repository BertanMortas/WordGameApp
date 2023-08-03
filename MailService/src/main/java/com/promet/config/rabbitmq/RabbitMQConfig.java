package com.promet.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.mailQueue}")
    String mailQueue;
    @Value("${rabbitmq.forgotPasswordQueue}")
    private String forgotPasswordQueue;
    @Bean
    Queue mailQueue(){return new Queue(mailQueue);}

    @Bean
    Queue forgotPasswordQueue(){
        return new Queue(forgotPasswordQueue);
    }

    }


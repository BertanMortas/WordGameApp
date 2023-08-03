package com.promet.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange}")
    String exchange;
    @Value("${rabbitmq.mailQueue}")
    String mailQueue;
    @Value("${rabbitmq.mailBindingKey}")
    String mailBindingKey;

    @Bean
    Queue mailQueue() {
        return new Queue(mailQueue);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingRegisterMail(final Queue mailQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(mailQueue).to(directExchange).with(mailBindingKey);
    }

    @Value("${rabbitmq.forgotPasswordQueue}")
    private String forgotPasswordQueue;
    @Value("${rabbitmq.forgotPasswordBindingKey}")
    private String forgotPasswordBindingKey;

    @Bean
    Queue forgotPasswordQueue(){
        return new Queue(forgotPasswordQueue);
    }

    @Bean
    public Binding bindingForgotPassword(final Queue forgotPasswordQueue, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(forgotPasswordQueue).to(exchangeAuth).with(forgotPasswordBindingKey);
    }



}

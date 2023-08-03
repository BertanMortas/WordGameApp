package com.promet.rabbitmq.producer;

import com.promet.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.mailBindingKey}")
    private String mailBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendActivationLink(RegisterMailModel model) {
        rabbitTemplate.convertAndSend(exchange,mailBindingKey,model);
    }
}

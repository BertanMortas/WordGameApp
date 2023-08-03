package com.promet.rabbitmq.consumer;

import com.promet.rabbitmq.model.ForgotPasswordMailModel;
import com.promet.rabbitmq.model.RegisterMailModel;
import com.promet.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {
    private final MailService mailService;

    @RabbitListener(queues = ("${rabbitmq.mailQueue}"))
    public void sendActivationLink(RegisterMailModel model) {mailService.createMail(model);}

    @RabbitListener(queues = ("${rabbitmq.forgotPasswordQueue}"))
    public void sendForgotPasswordMail(ForgotPasswordMailModel forgotPasswordMailModel){
        mailService.sendMailForgetPassword(forgotPasswordMailModel);
    }
}

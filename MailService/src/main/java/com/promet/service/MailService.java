package com.promet.service;

import com.promet.dto.request.ForgotPasswordRequestDto;
import com.promet.manager.IAuthManager;
import com.promet.rabbitmq.model.ForgotPasswordMailModel;
import com.promet.rabbitmq.model.RegisterMailModel;
import com.promet.utility.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final IAuthManager authManager;

    public void createMail(RegisterMailModel registerMailModel) {
        System.out.println("1");
        String token = jwtTokenProvider.createTokenForActivateLink(registerMailModel.getAuthId(), registerMailModel.getActivationCode())
                .orElseThrow(() -> {
                    throw new RuntimeException("Token üretilemedi");
                });
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Hesap aktivasyon linki");
        simpleMailMessage.setFrom("${spring.mail.username}");
        simpleMailMessage.setTo(registerMailModel.getEmail());
        simpleMailMessage.setText("Sayın " + registerMailModel.getName() + ", hesabınızı aktif etmek için aşağıdaki linke tıklayınız. \n"
                + "http://localhost:8060/api/v1/auth/activate-status/" + token); //TODO Url'in uzantısı tamamlanmadı. UNUTMA!!
        javaMailSender.send(simpleMailMessage);
    }
    
    public Boolean sendMailForgetPassword(ForgotPasswordMailModel forgotPasswordMailModel) {
        try{
            System.out.println("2");
            Optional<String> optionalToken = jwtTokenProvider.createToken(forgotPasswordMailModel.getAuthId());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("${spring.mail.username}");
            mailMessage.setTo(forgotPasswordMailModel.getEmail());
            mailMessage.setSubject("PASSWORD RESET EMAIL");
            mailMessage.setText("Dear: "+forgotPasswordMailModel.getEmail()+" this is your new password: "+forgotPasswordMailModel.getPassword());
            javaMailSender.send(mailMessage);
            ForgotPasswordRequestDto dto = ForgotPasswordRequestDto.builder()
                    .token(optionalToken.get())
                    .build();
            authManager.forgotPassword(dto);
        }catch (Exception e){
            e.getMessage();
        }
        return true;
    }
}

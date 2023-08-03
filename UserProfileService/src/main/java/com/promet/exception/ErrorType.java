package com.promet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_CREATED(4100, "Kullanıcı oluşturulamadı", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    TAKEOFF_NOT_FOUND(4400, "Böyle bir izin isteği bulunamadı", HttpStatus.NOT_FOUND),
    USER_NOT_AT_SAME_COMPANY(4400, "You are not in the same company", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4600,"Token hatası" ,  HttpStatus.BAD_REQUEST),
    AUTHORIZATION_ERROR(4605, "you have no permission to continue", HttpStatus.BAD_REQUEST),
    OUT_OFF_HOLIDAYS(4601,"You do not have enough vacation days left." ,  HttpStatus.BAD_REQUEST),
    INVALID_DATE(4700,"chosen date is not allowed" ,  HttpStatus.BAD_REQUEST),
    USER_NOT_FOLLOW(4800,"Kullanıcı kendisini takip edemez" ,  HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4900,"Girdiğiniz şifre eski şifre ile uyuşmamaktadır." ,  HttpStatus.BAD_REQUEST),
    NOT_AUTHORIZED(5000,"Bu işlemi yapmak için yetkiniz yok." ,  HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}

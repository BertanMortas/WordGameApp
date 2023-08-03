package com.promet.manager;

import com.promet.dto.request.ForgotPasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8060/api/v1/auth",name = "mail-auth")
public interface IAuthManager {

    @PutMapping("/forgot-password")
    ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPasswordRequestDto dto);
}

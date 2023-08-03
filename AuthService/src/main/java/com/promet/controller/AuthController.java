package com.promet.controller;

import com.promet.dto.request.*;
import com.promet.dto.response.LoginResponseDto;
import com.promet.exception.AuthManagerException;
import com.promet.exception.ErrorType;
import com.promet.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.promet.constant.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(REGISTER)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/activate-status/{token}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable String token) throws URISyntaxException {
        Boolean status = ResponseEntity.ok(authService.activateStatus(token)).getBody();
        if(status){
            URI activateStatus = new URI("http://localhost:3000/activate-user-mail");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(activateStatus);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
        throw new AuthManagerException(ErrorType.INTERNAL_ERROR);
    }

    @Hidden
    @PostMapping("/do-activate-manager")
    public ResponseEntity<Boolean> doActivateManager(@RequestBody FromUserProfileToAuthForActivateManager dto) {
        return ResponseEntity.ok(authService.doActivateManager(dto));
    }

    @PutMapping("/forgot-password/{email}/{name}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    ResponseEntity<Boolean> forgotPassword(@PathVariable String email, @PathVariable String name){
        return ResponseEntity.ok(authService.forgotPassword(email, name));
    }

    @Hidden
    @PutMapping("update-userprofile/{token}")
    public ResponseEntity<Boolean> updateUserProfile(@PathVariable String token, @RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateAuth(token, dto));
    }

    @Hidden
    @DeleteMapping("delete-auth/{authId}")
    public ResponseEntity<Boolean> deleteAuth(@PathVariable Long authId){
        return ResponseEntity.ok(authService.deleteAuth(authId));
    }

    @Hidden
    @PostMapping("/get-roles/{authId}")
    ResponseEntity<List<String>> getRoles(@PathVariable Long authId){
        return ResponseEntity.ok(authService.getRoles(authId));
    }
}

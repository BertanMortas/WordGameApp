package com.promet.manager;

import com.promet.dto.request.FromUserProfileToAuthAddEmployeeRequestDto;
import com.promet.dto.request.FromUserProfileToAuthForActivateManager;
import com.promet.dto.request.UserUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8060/api/v1/auth",name = "userprofile-auth")
public interface IAuthManager {
    @PostMapping("/do-activate-manager")
    ResponseEntity<Boolean> doActivateManager(@RequestBody FromUserProfileToAuthForActivateManager dto);

    @PostMapping("/create-employee")
    ResponseEntity<Long> createEmployee(@RequestBody FromUserProfileToAuthAddEmployeeRequestDto dto);
    @PutMapping("update-userprofile/{token}")
    ResponseEntity<Boolean> updateUserProfile(@PathVariable String token, @RequestBody UserUpdateRequestDto dto);

    @DeleteMapping("delete-auth/{authId}")
    ResponseEntity<Boolean> deleteAuth(@PathVariable Long authId);

    @PostMapping("/get-roles/{authId}")
    ResponseEntity<List<String>> getRoles(@PathVariable Long authId);
}

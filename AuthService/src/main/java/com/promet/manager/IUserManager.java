package com.promet.manager;

import com.promet.dto.request.FromAuthToUserProfileActivateManagerStatusRequestDto;
import com.promet.dto.request.FromAuthToUserProfileCreateUserRequestDto;
import com.promet.dto.request.UserprofileChangePasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8090/api/v1/user-profile", name = "auth-userprofile")
public interface IUserManager {

    @PostMapping("/create")
    ResponseEntity<Boolean> createUser(@RequestBody FromAuthToUserProfileCreateUserRequestDto dto);

    @PutMapping("/update-manager-status/{authId}")
    ResponseEntity<Boolean> updateManagerStatus(@PathVariable Long authId);

    @PutMapping("/activate-manager-status")
    ResponseEntity<Boolean> activateManagerStatus(@RequestBody FromAuthToUserProfileActivateManagerStatusRequestDto dto);
    @PutMapping("/forgot-password")
    ResponseEntity<Boolean> forgotPasswordUser(@RequestBody UserprofileChangePasswordRequestDto dto);
}

package com.promet.controller;

import com.promet.dto.request.*;
import com.promet.dto.response.*;
import com.promet.repository.entity.UserProfile;
import com.promet.service.UserProfileService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.promet.constant.ApiUrls.*;

@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    //@Hidden
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody CreateUserRequestDto dto) {
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("update-userprofile/{token}")
    public ResponseEntity<Boolean> updateUserProfile(@PathVariable String token, @RequestBody UserUpdateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(token, dto));
    }
    @Hidden
    @PutMapping("/update-manager-status/{authId}")
    ResponseEntity<Boolean> updateManagerStatus(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.updateManagerStatus(authId));
    }

    @PutMapping("/activate-manager-status")
    public ResponseEntity<Boolean> activateManagerStatus(@RequestBody FromAuthToUserProfileActivateManagerStatusRequestDto dto){
        return ResponseEntity.ok(userProfileService.activateManagerStatus(dto));
    }
    @PutMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPasswordUser(@RequestBody UserprofileChangePasswordRequestDto dto){
        return ResponseEntity.ok(userProfileService.forgotPasswordUser(dto));
    }

}

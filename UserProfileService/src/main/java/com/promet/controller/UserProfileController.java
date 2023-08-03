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
    @GetMapping(FIND_BY_ID+"/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<GetNameByIdResponseDto> findById(@PathVariable String userId){
        return ResponseEntity.ok(userProfileService.findNameById(userId));
    }
    @DeleteMapping("delete-employee/{token}/{email}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable String token,@PathVariable String email){
        return ResponseEntity.ok(userProfileService.deleteEmployee(token, email));
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


    @Hidden
    @GetMapping("/get-user-name-surname/{userId}")
    ResponseEntity<GetNameAndSurnameFromUserProfileResponseDto> getNameAndSurname(@PathVariable String userId){
        return ResponseEntity.ok(userProfileService.getNameAndSurname(userId));
    }


    @GetMapping("/get-user-profile/{token}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String token){
        return ResponseEntity.ok(userProfileService.getUserProfile(token));
    }


}

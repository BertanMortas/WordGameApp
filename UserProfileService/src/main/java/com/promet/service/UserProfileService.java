package com.promet.service;

import com.promet.dto.request.*;
import com.promet.dto.response.*;
import com.promet.exception.ErrorType;
import com.promet.exception.UserProfileManagerException;
import com.promet.manager.IAuthManager;
import com.promet.mapper.IUserProfileMapper;
import com.promet.repository.IUserProfileRepository;
import com.promet.repository.entity.UserProfile;
import com.promet.repository.entity.enums.EStatus;
import com.promet.utility.JwtTokenProvider;
import com.promet.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final IAuthManager authManager;

    public UserProfileService(IUserProfileRepository userProfileRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, IAuthManager authManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public Boolean createUser(CreateUserRequestDto dto) {
        UserProfile userProfile = IUserProfileMapper.INSTANCE.createUser(dto);
        save(userProfile);
        return true;
    }

    public Boolean updateUserProfile(String token, UserUpdateRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new UserProfileManagerException(ErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId.get());
        if (userProfile.isEmpty()) throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        update(IUserProfileMapper.INSTANCE.fromUpdateUserProfileToUserProfile(userProfile.get(), dto));
        authManager.updateUserProfile(token, dto);
        return true;
    }

    public Boolean updateManagerStatus(Long authId) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByAuthId(authId);
        if (optionalUserProfile.isEmpty())
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        optionalUserProfile.get().setStatus(EStatus.BANNED);
        update(optionalUserProfile.get());
        return true;
    }

    public Boolean activateManagerStatus(FromAuthToUserProfileActivateManagerStatusRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(dto.getAuthId());
        userProfile.get().setStatus(EStatus.INACTIVE);
        update(userProfile.get());
        return true;
    }
    public GetNameAndSurnameFromUserProfileResponseDto getNameAndSurname(String userId) {
        Optional<UserProfile> optionalUser = findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        return GetNameAndSurnameFromUserProfileResponseDto.builder()
                .name(optionalUser.get().getName())
                .surname(optionalUser.get().getSurname())
                .build();

    }
    public Boolean forgotPasswordUser(UserprofileChangePasswordRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(dto.getAuthId());
        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setPassword(dto.getPassword());
        update(userProfile.get());
        return true;
    }
}

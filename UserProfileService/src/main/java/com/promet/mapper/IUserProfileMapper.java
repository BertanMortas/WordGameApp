package com.promet.mapper;

import com.promet.dto.request.*;
import com.promet.dto.response.GetNameByIdResponseDto;
import com.promet.repository.entity.UserProfile;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile createUser(final CreateUserRequestDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile fromUpdateUserProfileToUserProfile(@MappingTarget UserProfile userProfile, final UserUpdateRequestDto dto);
    GetNameByIdResponseDto toGetNameDto (final UserProfile userProfile);
}

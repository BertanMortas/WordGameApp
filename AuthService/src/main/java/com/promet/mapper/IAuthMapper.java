package com.promet.mapper;

import com.promet.dto.request.*;
import com.promet.dto.response.RegisterResponseDto;
import com.promet.rabbitmq.model.RegisterMailModel;
import com.promet.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    FromAuthToUserProfileCreateUserRequestDto fromAuthToCreateUserRequestDto(final Auth auth);

    Auth fromRegisterRequestDtoToAuth(final RegisterRequestDto dto);

    RegisterMailModel fromAuthToRegisterMailModel(final Auth auth);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromUserUpdateRequestDtotoAuth(@MappingTarget Auth auth, final UserUpdateRequestDto dto);
}

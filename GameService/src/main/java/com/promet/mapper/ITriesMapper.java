package com.promet.mapper;

import com.promet.dto.request.CreateTriesRequestDto;
import com.promet.repository.entity.Tries;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITriesMapper {
    ITriesMapper INSTANCE = Mappers.getMapper(ITriesMapper.class);
    Tries toTries(final CreateTriesRequestDto dto);
}

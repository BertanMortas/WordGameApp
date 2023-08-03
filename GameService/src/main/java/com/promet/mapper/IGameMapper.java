package com.promet.mapper;

import com.promet.dto.request.CreateGameRequestDto;
import com.promet.repository.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGameMapper {
    IGameMapper INSTANCE = Mappers.getMapper(IGameMapper.class);
    Game toGame(final CreateGameRequestDto dto);
}

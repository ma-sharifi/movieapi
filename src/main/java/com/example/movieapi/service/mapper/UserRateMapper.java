package com.example.movieapi.service.mapper;

import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Mahdi Sharifi
 * @since 10/10/22
 * Mapper for the entity {@link UserRateMapper} and its DTO {@link UserRateDto}.
 */

@Mapper(componentModel = "spring")
public interface UserRateMapper{

    @Mapping(source = "id.imdbId", target = "imdbId")
    @Mapping(source = "id.username", target = "username")
    UserRateDto toDto(UserRate entity);

    @Mapping(source = "imdbId", target = "id.imdbId")
    @Mapping(source = "username", target = "id.username")
    UserRate toEntity(UserRateDto dto);
}

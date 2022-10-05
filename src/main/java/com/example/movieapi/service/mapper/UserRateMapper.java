package com.example.movieapi.service.mapper;

import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;
import org.mapstruct.Mapper;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 *
 *  Mapper for the entity {@link UserRateMapper} and its DTO {@link UserRateDto}.
 */

@Mapper(componentModel = "spring")
public interface UserRateMapper extends EntityMapper<UserRateDto, UserRate> {

}

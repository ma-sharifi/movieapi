package com.example.movieapi.service.mapper;

import com.example.movieapi.service.dto.UserRateDto;

import java.math.BigInteger;

/**
 * @author Mahdi Sharifi
 * @since 10/10/22
 */
public enum GeneralMapper {
    INSTANCE;
    public  static UserRateDto toUserRateDto(Object[] userRateRetreive) {
        return UserRateDto.builder()
                .imdbId((String) userRateRetreive[0])
                .title((String) userRateRetreive[1])
                .boxOffice((BigInteger) userRateRetreive[2])
                .rateAverage((double) userRateRetreive[3])
                .build();
    }
}

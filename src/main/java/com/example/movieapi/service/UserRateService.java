package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;

import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/11/22
 */
public interface UserRateService {
    UserRateDto save(UserRate userRate);

    UserRateDto findOne(UserMovieId id);

    List<UserRateDto> findTopTenOrderedByBoxOffice();
}

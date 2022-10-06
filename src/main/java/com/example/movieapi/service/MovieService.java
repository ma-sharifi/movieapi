package com.example.movieapi.service;

import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.UserRateDto;

import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
public interface MovieService {
    /**
     * Check CSV for Best Picture and integrate them together
     * if won then find the information of movie on API and return it
     *
     * Due to the lack of Category in API, file was checked for "Best Picture"
     * API->Title": "Inception", "Awards": "Won 4 Oscars. 157 wins & 220 nominations total",
     * API->"Title": "Black Swan", "Awards": "Won 1 Oscar. 97 wins & 280 nominations total",
     * CSV-> 2010 (83rd),Best Picture,Black Swan,"Mike Medavoy, Brian Oliver and Scott Franklin, Producers",NO,
     */
    OmdbResponseDto isWonOscar(String title);
    /**
     * Rate the movie and persis the rate in local DB
     */
    OmdbResponseDto rateByTitle(UserRateDto rateRequestDto, String user);

    /**
     * Rate the movie and persis the rate in local DB
     */
    UserRateDto rateByImdbId(String imdbId, int rate, String user);

    /**
     * Rate the movie and persis the rate in local DB
     */
    List<OmdbResponseDto> findTopTen();
}

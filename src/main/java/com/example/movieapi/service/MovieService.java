package com.example.movieapi.service;

import com.example.movieapi.exception.MovieNotFoundException;
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
     * <p>
     * Due to the lack of Category in Omdb API, file was checked for "Best Picture"
     * API->"Title": "Inception", "Awards": "Won 4 Oscars. 157 wins & 220 nominations total",
     * API->"Title": "Black Swan", "Awards": "Won 1 Oscar. 97 wins & 280 nominations total",
     * CSV-> 2010 (83rd),Best Picture,Black Swan,"Mike Medavoy, Brian Oliver and Scott Franklin, Producers",NO,
     * CSV-> 2010 (83rd),Best Picture,The King's Speech,"Iain Canning, Emile Sherman and Gareth Unwin, Producers",YES,,,,,,
     */
    OmdbResponseDto isWonOscar(String title);

    /**
     * Rate the movie and persis the rate in local DB
     */
    UserRateDto rateByTitle(String title, int rate, String user) throws MovieNotFoundException;

    /**
     * Rate the movie and persis the rate in local DB
     */
    List<UserRateDto> findTopTen();


}

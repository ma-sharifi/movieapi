package com.example.movieapi.service;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.service.dto.OmdbResponseDto;

import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/11/22
 */
public interface OmdbService {
    Optional<OmdbResponseDto> getSingleMovieByTitle(String title) throws MovieNotFoundException;

    Optional<OmdbResponseDto> getSingleMovieByImdbId(String imdbId);
}

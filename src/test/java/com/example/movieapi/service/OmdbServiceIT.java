package com.example.movieapi.service;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.impl.OmdbServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@SpringBootTest
class OmdbServiceIT {

    @Autowired
    private OmdbServiceImpl omdbService;

    @Test
    void shouldReturnOmdbResponseDto_whenSingleMovieByTitleIsCalled() {
        Optional<OmdbResponseDto> omdbResponseDtoOptional= omdbService.getSingleMovieByTitle("Black Swan");
            omdbResponseDtoOptional.ifPresent(result->
            {
                assertEquals("Black Swan", omdbResponseDtoOptional.get().getTitle());
                assertEquals("2010", omdbResponseDtoOptional.get().getYear());
                assertEquals("tt0947798", omdbResponseDtoOptional.get().getImdbID());
                assertEquals("Darren Aronofsky", omdbResponseDtoOptional.get().getDirector());
                assertEquals("Won 1 Oscar. 97 wins & 280 nominations total", omdbResponseDtoOptional.get().getAwards());
            });

    }
    @Test
    void shouldReturnOmdbResponseDto_whenGetSingleMovieByTitleIsCalled() {
        Optional<OmdbResponseDto> omdbResponseDtoOptional= omdbService.getSingleMovieByTitle("Black Swan");
        if(!omdbResponseDtoOptional.isPresent()) {
            assertNull(omdbResponseDtoOptional.get());
        }
    }

    @Test
    void shouldReturnMovieNotFoundException_whenGetSingleMovieByTitleIsCalled() {
        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            omdbService.getSingleMovieByTitle("Hello Mahdi");
        });
        // Assert
        assertTrue(thrown.getMessage().contains("not find the movie"));
    }

    @Test
    void  shouldReturnOmdbResponseDto_whenGetSingleMovieByImdbIdIsCalled() {
        Optional<OmdbResponseDto> omdbResponseDtoOptional= omdbService.getSingleMovieByImdbId("tt0947798");
        if(omdbResponseDtoOptional.isPresent()) {
            OmdbResponseDto responseDto=omdbResponseDtoOptional.get();
            assertEquals("Black Swan", responseDto.getTitle());
            assertEquals("2010", responseDto.getYear());
            assertEquals("tt0947798", responseDto.getImdbID());
            assertEquals("Darren Aronofsky", responseDto.getDirector());
            assertEquals("Won 1 Oscar. 97 wins & 280 nominations total", responseDto.getAwards());
        }
    }
}
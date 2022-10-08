package com.example.movieapi.service;

import com.example.movieapi.service.dto.OmdbResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@SpringBootTest
class OmdbServiceIT {

    @Autowired
    private OmdbService omdbService;

    @Test
    void should_return_singleMovieByTitle() {
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
    void should_return_nothing() {
        Optional<OmdbResponseDto> omdbResponseDtoOptional= omdbService.getSingleMovieByTitle("Hello");
        if(!omdbResponseDtoOptional.isPresent()) {
            assertNull(omdbResponseDtoOptional.get());
        }
    }

    @Test
    void  should_return_singleMovieById() {
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
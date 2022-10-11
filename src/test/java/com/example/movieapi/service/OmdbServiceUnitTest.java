package com.example.movieapi.service;

import com.example.movieapi.exception.OmdbApiException;
import com.example.movieapi.service.dto.OmdbResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Mahdi Sharifi
 * @since 10/11/22
 *
 * Test the OmdbService without any dependency to Omdb API. It means Solidarity Test
 */
@ExtendWith(MockitoExtension.class)
class OmdbServiceUnitTest {

    OmdbService omdbService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${omdbapi.apikey}")
    private String apiKey;

    @BeforeEach
    void initializeService() {
        omdbService = new OmdbService(restTemplate);
    }

    @Test
    void shouldReturnOmdbResponseDto_whenGetSingleMovieByImdbIdIsCalled() {

        OmdbResponseDto omdbResponseStubDto = new OmdbResponseDto();
        omdbResponseStubDto.setTitle("Black Swan");
        omdbResponseStubDto.setYear("2010");
        omdbResponseStubDto.setImdbID("tt0947798");
        omdbResponseStubDto.setDirector("Darren Aronofsky");
        omdbResponseStubDto.setAwards("Won 1 Oscar. 97 wins & 280 nominations total");
        omdbResponseStubDto.setResponse("True");

        // Arrange
        when(restTemplate.getForEntity("null/?i={imdbId}&apiKey={apiKey}", OmdbResponseDto.class, "IMDB_ID", apiKey))
                .thenReturn(new ResponseEntity(omdbResponseStubDto, HttpStatus.OK));

        // Act
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByImdbId("IMDB_ID");

        // Assert
        omdbResponseDtoOptional.ifPresent(result ->
        {
            assertEquals("Black Swan", omdbResponseDtoOptional.get().getTitle());
            assertEquals("2010", omdbResponseDtoOptional.get().getYear());
            assertEquals("tt0947798", omdbResponseDtoOptional.get().getImdbID());
            assertEquals("Darren Aronofsky", omdbResponseDtoOptional.get().getDirector());
            assertEquals("Won 1 Oscar. 97 wins & 280 nominations total", omdbResponseDtoOptional.get().getAwards());
            assertEquals("True", omdbResponseDtoOptional.get().getResponse());
        });
    }

    @Test
    void shouldReturnOmdbResponseDto_whenGetSingleMovieByTitleIsCalled() throws Exception {

        OmdbResponseDto omdbResponseStubDto = new OmdbResponseDto();
        omdbResponseStubDto.setTitle("Black Swan");
        omdbResponseStubDto.setYear("2010");
        omdbResponseStubDto.setImdbID("tt0947798");
        omdbResponseStubDto.setDirector("Darren Aronofsky");
        omdbResponseStubDto.setAwards("Won 1 Oscar. 97 wins & 280 nominations total");
        omdbResponseStubDto.setResponse("True");

        // Arrange
        when(restTemplate.getForEntity("null/?t={title}&apiKey={apiKey}", OmdbResponseDto.class, "TITLE", apiKey))
                .thenReturn(new ResponseEntity(omdbResponseStubDto, HttpStatus.OK));

        // Act
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByTitle("TITLE");

        // Assert
        omdbResponseDtoOptional.ifPresent(result ->
        {
            assertEquals("Black Swan", omdbResponseDtoOptional.get().getTitle());
            assertEquals("2010", omdbResponseDtoOptional.get().getYear());
            assertEquals("tt0947798", omdbResponseDtoOptional.get().getImdbID());
            assertEquals("Darren Aronofsky", omdbResponseDtoOptional.get().getDirector());
            assertEquals("Won 1 Oscar. 97 wins & 280 nominations total", omdbResponseDtoOptional.get().getAwards());
            assertEquals("True", omdbResponseDtoOptional.get().getResponse());
        });
    }

    @Test
    void shouldReturnOmdbApiException_whenGetSingleMovieByImdbIdIsCalled() {

        // Arrange
        when(restTemplate.getForEntity("null/?t={title}&apiKey={apiKey}", OmdbResponseDto.class, "TITLE", apiKey))
                .thenThrow(new OmdbApiException("IMDB_ID"));

        OmdbApiException thrown = Assertions.assertThrows(OmdbApiException.class, () -> {
            // Act
            omdbService.getSingleMovieByTitle("TITLE");
        });
        // Assert
        assertTrue(thrown.getMessage().contains("is an error in calling Omdb API"));
    }

}

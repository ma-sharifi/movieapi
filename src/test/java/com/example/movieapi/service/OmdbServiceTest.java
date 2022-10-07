package com.example.movieapi.service;

import com.example.movieapi.service.dto.OmdbResponseDto;
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
class OmdbServiceTest {

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

    String Black_Swan="// 20221005113739\n" +
            "// https://www.omdbapi.com/?t=swan&apikey=c732beed\n" +
            "\n" +
            "{\n" +
            "  \"Title\": \"Black Swan\",\n" +
            "  \"Year\": \"2010\",\n" +
            "  \"Rated\": \"R\",\n" +
            "  \"Released\": \"17 Dec 2010\",\n" +
            "  \"Runtime\": \"108 min\",\n" +
            "  \"Genre\": \"Drama, Thriller\",\n" +
            "  \"Director\": \"Darren Aronofsky\",\n" +
            "  \"Writer\": \"Mark Heyman, Andres Heinz, John J. McLaughlin\",\n" +
            "  \"Actors\": \"Natalie Portman, Mila Kunis, Vincent Cassel\",\n" +
            "  \"Plot\": \"A committed dancer struggles to maintain her sanity after winning the lead role in a production of Tchaikovsky's \\\"Swan Lake\\\".\",\n" +
            "  \"Language\": \"English, French, Italian\",\n" +
            "  \"Country\": \"United States\",\n" +
            "  \"Awards\": \"Won 1 Oscar. 97 wins & 280 nominations total\",\n" +
            "  \"Poster\": \"https://m.media-amazon.com/images/M/MV5BNzY2NzI4OTE5MF5BMl5BanBnXkFtZTcwMjMyNDY4Mw@@._V1_SX300.jpg\",\n" +
            "  \"Ratings\": [\n" +
            "    {\n" +
            "      \"Source\": \"Internet Movie Database\",\n" +
            "      \"Value\": \"8.0/10\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Source\": \"Rotten Tomatoes\",\n" +
            "      \"Value\": \"85%\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Source\": \"Metacritic\",\n" +
            "      \"Value\": \"79/100\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"Metascore\": \"79\",\n" +
            "  \"imdbRating\": \"8.0\",\n" +
            "  \"imdbVotes\": \"759,022\",\n" +
            "  \"imdbID\": \"tt0947798\",\n" +
            "  \"Type\": \"movie\",\n" +
            "  \"DVD\": \"29 Mar 2011\",\n" +
            "  \"BoxOffice\": \"$106,954,678\",\n" +
            "  \"Production\": \"N/A\",\n" +
            "  \"Website\": \"N/A\",\n" +
            "  \"Response\": \"True\"\n" +
            "}";
}
package com.example.movieapi.service.impl;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.exception.MovieWasNotWonException;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/10/22
 */
@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    void ShouldReturnTrue_WhenIsWonOscarCalled() {
        OmdbResponseDto omdbResponseDto=movieService.isWonOscar("The Hurt Locker");//"Slumdog Millionaire"

        assertEquals("The Hurt Locker",omdbResponseDto.getTitle());
        assertEquals("tt0887912",omdbResponseDto.getImdbID());
        assertEquals("Kathryn Bigelow",omdbResponseDto.getDirector());
        assertEquals("2008",omdbResponseDto.getYear());
    }
    @Test
    void ShouldThrowMovieWasNotWonException_WhenMovieIsNotInCsvFile() {

        MovieWasNotWonException thrown = Assertions.assertThrows(MovieWasNotWonException.class, () -> {
            movieService.isWonOscar("Black Swan");
        });

        assertTrue(thrown.getMessage().contains("was not won an Oscar"));
    }

    @Test
    void ShouldThrowMovieNotFoundException_WhenMovieIsNotInCsvFile() {

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            movieService.isWonOscar("Hello Mahdi");
        });
        assertTrue(thrown.getMessage().contains("not find movie with"));
    }

    @Test
    void ShouldReturnUserRate_WhenRateByTitleIsCalled() {
        UserRateDto userRateDto= movieService.rateByTitle("Black Swan",10,"mahdi");
        assertEquals("Black Swan", userRateDto.getTitle());
        assertEquals("tt0947798", userRateDto.getImdbId());
        assertEquals( BigInteger.valueOf(106954678L), userRateDto.getBoxOffice());
        assertEquals(10, userRateDto.getRate());
        assertEquals("mahdi", userRateDto.getUsername());
    }

    @Test
    void ShouldThrowMovieNotFoundException_WhenRateByTitleIsCalled() {

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            movieService.rateByTitle("Hello Mahdi",10,"mahdi");
        });
        assertTrue(thrown.getMessage().contains("not find movie with"));
    }

    @Test
    void findTopTen() {
        List<UserRate> list=List.of(new UserRate(
                        new UserMovieId("tt0947798","mahdi"),0,"Black Swan",0L)
                ,new UserRate(new UserMovieId("tt0299658","mahdi"),1,"Chicago",1L)
                ,new UserRate(new UserMovieId("tt1375666","mahdi"),2,"Inception",2L)
                ,new UserRate(new UserMovieId("tt1504320","mahdi"),3,"The King's Speech",3L)
                ,new UserRate(new UserMovieId("tt1542344","mahdi"),4,"127 Hours",4L)
                ,new UserRate(new UserMovieId("tt0268978","mahdi"),8,"A Beautiful Mind",5L)
                ,new UserRate(new UserMovieId("tt0268978","ali"),10,"A Beautiful Mind",5L)
                ,new UserRate(new UserMovieId("tt0119217","mahdi"),7,"Good Will Hunting",6L)
                ,new UserRate(new UserMovieId("tt1014759","mahdi"),7,"Alice in Wonderland",7L)
                ,new UserRate(new UserMovieId("tt0371746","mahdi"),9,"Iron Man",8L)
                ,new UserRate(new UserMovieId("tt0887912","mahdi"),9,"The Hurt Locker",9L)
                ,new UserRate(new UserMovieId("tt0405159","mahdi"),10,"Million Dollar Baby",10L)
                ,new UserRate(new UserMovieId("tt0405159","mahdi"),10,"Million Dollar Baby",10L)
                ,new UserRate(new UserMovieId("tt0077869","mahdi"),10,"The Lord of the Rings",11L)
                ,new UserRate(new UserMovieId("tt0120689","mahdi"),10,"The Green Mile",12L)
                ,new UserRate(new UserMovieId("tt0120689","ali"),10,"The Green Mile",12L)
                ,new UserRate(new UserMovieId("tt0120689","Hasan"),10,"The Green Mile",12L)
        );
//         movieService.rateByTitle(list.get(0).getTitle(),list.get(0).getRate(),list.get(0).getId().getUsername());
//         movieService.rateByTitle(list.get(1).getTitle(),list.get(1).getRate(),list.get(1).getId().getUsername());
//         movieService.rateByTitle(list.get(2).getTitle(),list.get(2).getRate(),list.get(2).getId().getUsername());
        list.forEach(movie->movieService.rateByTitle(movie.getTitle(),movie.getRate(),movie.getId().getUsername()));

        List<UserRateDto> listTopTen= movieService.findTopTen();
        assertEquals("The Green Mile",listTopTen.get(0).getTitle());
        assertEquals("tt0120689",listTopTen.get(0).getImdbId());
        assertEquals(BigInteger.valueOf(12),listTopTen.get(0).getBoxOffice());
        assertEquals(10.0,listTopTen.get(0).getRateAverage(),0.1);
//        {
//            "payload": [
//            {
//                "title": "The Green Mile",
//                    "url": "http://localhost:8080/v1/movies/tt0120689",
//                    "imdb_id": "tt0120689",
//                    "rate_average": 10.0,
//                    "box_office": 12
//            },
//            {
//                "title": "The Lord of the Rings",
//                    "url": "http://localhost:8080/v1/movies/tt0077869",
//                    "imdb_id": "tt0077869",
//                    "rate_average": 10.0,
//                    "box_office": 11
//            },
//            {
//                "title": "Million Dollar Baby",
//                    "url": "http://localhost:8080/v1/movies/tt0405159",
//                    "imdb_id": "tt0405159",
//                    "rate_average": 10.0,
//                    "box_office": 10
//            },
//            {
//                "title": "The Hurt Locker",
//                    "url": "http://localhost:8080/v1/movies/tt0887912",
//                    "imdb_id": "tt0887912",
//                    "rate_average": 9.0,
//                    "box_office": 9
//            },
//            {
//                "title": "Iron Man",
//                    "url": "http://localhost:8080/v1/movies/tt0371746",
//                    "imdb_id": "tt0371746",
//                    "rate_average": 9.0,
//                    "box_office": 8
//            },
//            {
//                "title": "A Beautiful Mind",
//                    "url": "http://localhost:8080/v1/movies/tt0268978",
//                    "imdb_id": "tt0268978",
//                    "rate_average": 9.0,
//                    "box_office": 5
//            },
//            {
//                "title": "Alice in Wonderland",
//                    "url": "http://localhost:8080/v1/movies/tt1014759",
//                    "imdb_id": "tt1014759",
//                    "rate_average": 7.0,
//                    "box_office": 7
//            },
//            {
//                "title": "Good Will Hunting",
//                    "url": "http://localhost:8080/v1/movies/tt0119217",
//                    "imdb_id": "tt0119217",
//                    "rate_average": 7.0,
//                    "box_office": 6
//            },
//            {
//                "title": "127 Hours",
//                    "url": "http://localhost:8080/v1/movies/tt1542344",
//                    "imdb_id": "tt1542344",
//                    "rate_average": 4.0,
//                    "box_office": 4
//            },
//            {
//                "title": "The King's Speech",
//                    "url": "http://localhost:8080/v1/movies/tt1504320",
//                    "imdb_id": "tt1504320",
//                    "rate_average": 3.0,
//                    "box_office": 3
//            }
//    ],
//            "error_code": 0
//        }
    }
}
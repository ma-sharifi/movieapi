package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.exception.MovieWasNotWonException;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
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
        System.out.println("#thrown: "+thrown.getMessage() );
        assertTrue(thrown.getMessage().contains("not find the movie"));
    }

    @Test
    void ShouldReturnUserRate_WhenRateByTitleIsCalled() {
        UserRateDto userRateDto= movieService.rateByTitle("Black Swan",1,"mahdi");
        assertEquals("Black Swan", userRateDto.getTitle());
        assertEquals("tt0947798", userRateDto.getImdbId());
        assertEquals( BigInteger.valueOf(106954678L), userRateDto.getBoxOffice());
        assertEquals(1, userRateDto.getRate());
        assertEquals("mahdi", userRateDto.getUsername());
    }

    @Test
    void ShouldThrowMovieNotFoundException_WhenRateByTitleIsCalled() {

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            movieService.rateByTitle("Hello Mahdi",10,"mahdi");
        });
        assertTrue(thrown.getMessage().contains("not find the movie"));
    }

    @Test
    void ShouldReturnListOfTopTenMovie_WhenTopTenIsCalled() {
        List<UserRateDto> listTopTen= movieService.findTopTen();
        assertEquals("The Green Mile",listTopTen.get(0).getTitle());
        assertEquals("tt0120689",listTopTen.get(0).getImdbId());
        assertEquals(BigInteger.valueOf(12),listTopTen.get(0).getBoxOffice());
        assertEquals(10.0,listTopTen.get(0).getRateAverage(),0.1);

        assertEquals( "The Lord of the Rings",listTopTen.get(1).getTitle());
        assertEquals("tt0077869",listTopTen.get(1).getImdbId());
        assertEquals( 10.0,listTopTen.get(1).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(11),listTopTen.get(1).getBoxOffice());

        assertEquals( "Million Dollar Baby",listTopTen.get(2).getTitle());
        assertEquals("tt0405159",listTopTen.get(2).getImdbId());
        assertEquals( 10.0,listTopTen.get(2).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(10),listTopTen.get(2).getBoxOffice());

        assertEquals( "The Hurt Locker",listTopTen.get(3).getTitle());
        assertEquals("tt0887912",listTopTen.get(3).getImdbId());
        assertEquals( 9.0,listTopTen.get(3).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(9),listTopTen.get(3).getBoxOffice());

        assertEquals( "Iron Man",listTopTen.get(4).getTitle());
        assertEquals("tt0371746",listTopTen.get(4).getImdbId());
        assertEquals( 9.0,listTopTen.get(4).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(8),listTopTen.get(4).getBoxOffice());

        assertEquals( "A Beautiful Mind",listTopTen.get(5).getTitle());
        assertEquals("tt0268978",listTopTen.get(5).getImdbId());
        assertEquals( 9.0,listTopTen.get(5).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(5),listTopTen.get(5).getBoxOffice());


        assertEquals( "Alice in Wonderland",listTopTen.get(6).getTitle());
        assertEquals( "tt1014759",listTopTen.get(6).getImdbId());
        assertEquals( 7.0,listTopTen.get(6).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(7),listTopTen.get(6).getBoxOffice());

        assertEquals( "Good Will Hunting",listTopTen.get(7).getTitle());
        assertEquals( "tt0119217",listTopTen.get(7).getImdbId());
        assertEquals( 7.0,listTopTen.get(7).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(6),listTopTen.get(7).getBoxOffice());

        assertEquals( "127 Hours",listTopTen.get(8).getTitle());
        assertEquals( "tt1542344",listTopTen.get(8).getImdbId());
        assertEquals( 4.0,listTopTen.get(8).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(4),listTopTen.get(8).getBoxOffice());

        assertEquals( "The King's Speech",listTopTen.get(9).getTitle());
        assertEquals(  "tt1504320",listTopTen.get(9).getImdbId());
        assertEquals( 3.0,listTopTen.get(9).getRateAverage(),0.1);
        assertEquals(BigInteger.valueOf(3),listTopTen.get(9).getBoxOffice());
    }
}
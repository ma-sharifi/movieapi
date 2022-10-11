package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.service.dto.OscarWinnerCsvDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */

@SpringBootTest
class OscarWinnerCsvServiceTest {

    @Autowired
   private OscarWinnerCsvService oscarWinnerService;


    @Test
    void should_ReturnSize() {
        assertEquals(10137,oscarWinnerService.getSize());
        assertNotEquals(0,oscarWinnerService.getSize());
    }

    @Test
    void Should_ReturnTrue_When_MovieWon() {
        assertTrue(oscarWinnerService.findMovieByTitleAndCategory("The Hurt Locker").getWon());
        assertTrue(oscarWinnerService.findMovieByTitleAndCategory("Slumdog Millionaire").getWon());
    }
    @Test
    void ShouldThrowMovieNotFoundException_When_MovieIsNotInCsvFile() {

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            // Act
            oscarWinnerService.findMovieByTitleAndCategory("Hello Mahdi");
        });
        // Assert
        assertTrue(thrown.getMessage().contains("not find the movie"));

    }

    @Test
    void Should_ReturnFalse_When_MovieNotWin() {
        assertFalse(oscarWinnerService.findMovieByTitleAndCategory("Black Swan").getWon());
    }

    @Test
    void Should_ReturnList_When_TitleFind() {
        String title = "Inception";
        List list=oscarWinnerService.findByTitle(title);
        list.forEach(x-> System.out.println(x.toString()));
    }

}
package com.example.movieapi.service;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.service.impl.OscarWinnerCsvServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */

@SpringBootTest
class OscarWinnerCsvServiceTest {

    @Autowired
    private OscarWinnerCsvServiceImpl oscarWinnerService;


    @Test
    void shouldReturnSize_whenGetSizeIsCalled() {
        assertEquals(10137, oscarWinnerService.getSize());
        assertNotEquals(0, oscarWinnerService.getSize());
    }

    @Test
    void ShouldReturnTrue_WhenMovieWon() {
        assertTrue(oscarWinnerService.findMovieByTitleAndCategory("The Hurt Locker").getWon());
        assertTrue(oscarWinnerService.findMovieByTitleAndCategory("Slumdog Millionaire").getWon());
    }

    @Test
    void ShouldThrowMovieNotFoundException_WhenMovieDoesNotExistCalled() {

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            // Act
            oscarWinnerService.findMovieByTitleAndCategory("Hello Mahdi");
        });
        // Assert
        assertTrue(thrown.getMessage().contains("not find the movie"));

    }

    @Test
    void ShouldReturnFalse_WhenMovieNotWin() {
        assertFalse(oscarWinnerService.findMovieByTitleAndCategory("Black Swan").getWon());
    }

}
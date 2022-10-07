package com.example.movieapi.service;

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
        assertTrue(oscarWinnerService.isWonByTitleForBestPicture("The Hurt Locker"));
        assertTrue(oscarWinnerService.isWonByTitleForBestPicture("Slumdog Millionaire"));
    }
    @Test
    void Should_ReturnFalse_When_MovieNotWin() {
        assertFalse(oscarWinnerService.isWonByTitleForBestPicture("Iron Man"));
        assertFalse(oscarWinnerService.isWonByTitleForBestPicture("Black Swan"));
    }

    @Test
    void Should_ReturnList_When_TitleFind() {
        String title = "Inception";
        List list=oscarWinnerService.findByTitle(title);
        list.forEach(x-> System.out.println(x.toString()));
    }

}
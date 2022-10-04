package com.example.movieapi.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class OscarWinnerServiceTest {

    @Autowired
   private OscarWinnerService oscarWinnerService;


    @Test
    void should_ReturnSize() {
        assertEquals(10137,oscarWinnerService.getSize());
        assertNotEquals(0,oscarWinnerService.getSize());
    }

    @Test
    void Should_ReturnTrue_When_MovieWon() {
        String title = "The Hurt Locker";
        assertTrue(oscarWinnerService.isWonByTitleForBestPicture(title));
    }
    @Test
    void Should_ReturnFalse_When_MovieNotWin() {
        String title = "Inception";
        assertFalse(oscarWinnerService.isWonByTitleForBestPicture(title));
    }

    @Test
    void Should_ReturnList_When_TitleFind() {
        String title = "Inception";
        List list=oscarWinnerService.findByTitle(title);
        list.forEach(x-> System.out.println(x.toString()));
    }

}
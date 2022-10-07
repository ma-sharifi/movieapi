package com.example.movieapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */

class UserRateTest {

    UserRate userRate;

    @BeforeEach
    void setUp() {
        userRate = new UserRate();
    }

    @Test
    void shouldReturnUserRate() {
        String title = "Black Swan";
        UserMovieId id=new UserMovieId("tt1375666","test-user");
        userRate.setTitle(title);
        userRate.setId(id);
        assertEquals(title, userRate.getTitle());
        assertEquals(id, userRate.getId());
    }

}

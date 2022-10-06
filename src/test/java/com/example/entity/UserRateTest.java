package com.example.entity;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
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

//    @Test
//    void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(UserRate.class);
//        UserRate userRate1 = new UserRate();
//        userRate1.setTitle("Black Swan");
//
//        UserRate userRate2 = new UserRate();
//        userRate2.setTitle(userRate1.getTitle());
//
//        assertThat(userRate1).isEqualTo(userRate2);
//        userRate2.setTitle("Inception");
//        assertThat(userRate1).isNotEqualTo(userRate2);
//        userRate1.setTitle(null);
//        assertThat(userRate1).isNotEqualTo(userRate2);
//    }
}

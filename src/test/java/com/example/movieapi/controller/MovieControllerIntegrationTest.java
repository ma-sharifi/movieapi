package com.example.movieapi.controller;

import com.example.movieapi.IntegrationTest;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.BadRequestAlertException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@IntegrationTest
public class MovieControllerIntegrationTest {

    private static final String DEFAULT_TITLE = "Black Swan";
    private static final String ENTITY_API_URL = "/v1/movies";
    private static final String WON_URL = ENTITY_API_URL + "/won";
    private static final String RATE_URL = ENTITY_API_URL + "/rate";
    private static final String TOP_TEN_URL = ENTITY_API_URL + "/top-ten";
    private static final String JWT = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQ3NjMzNywiZXhwIjoxNjY4MDcxOTM3fQ.Iu51zoIehYE96unfQ-UA_sYlMV6aMs4qWIwMvJJxBbty_C-7GvJJZ12I5TBVjorLUnquE2t4O0WuKR4QJp6RqA";

    @Autowired
    private UserRateRepository userRateRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRateMapper userRateMapper;

    private UserRate userRate;

    /**
     * Create an entity for this test.
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRate createEntity() {
        UserRate userRate = new UserRate();
        userRate.setTitle(DEFAULT_TITLE);
        return userRate;
    }

    @BeforeEach
    public void initTest() {
        userRate = createEntity();
    }

    @Test
    void shouldReturnMovieInfo_whengetMovieFromOmdbApiIsCalledByTitle() throws Exception {
        mockMvc
                .perform(
                        get(ENTITY_API_URL + "/tt0887912")//Black Swan
                                .header("Authorization", JWT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Title").value("The Hurt Locker"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Year").value("2008"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].imdbID").value("tt0887912"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Director").value("Kathryn Bigelow"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Awards").value("Won 6 Oscars. 125 wins & 130 nominations total"));
    }

    @Test
    void shouldReturnMovieInfo_whenMovieWonOscarIsCalledByTitle() throws Exception {
        mockMvc
                .perform(
                        get(WON_URL + "?title=The Hurt Locker")//Black Swan
                                .header("Authorization", JWT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Title").value("The Hurt Locker"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Year").value("2008"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].imdbID").value("tt0887912"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Director").value("Kathryn Bigelow"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Awards").value("Won 6 Oscars. 125 wins & 130 nominations total"));
    }

    @Test
    void shouldReturnThrowNotFoundException_whenNotWon() throws Exception {
        mockMvc
                .perform(
                        get(WON_URL + "?title=Black Swan")
                                .header("Authorization", JWT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_code").value(1));
    }

    @Test
    void shouldReturnBadRequestAlertException_whenRateMovieWithWrongRateIsCalled() throws Exception {
        userRate.setRate(0);
        userRate.setTitle("Lost in Translation"); // Its a new Movie
        // Create the UserRate
        UserRateDto userRateDto = userRateMapper.toDto(userRate);
        Gson gson = new Gson();
        String userRateDtoJsonString = gson.toJson(userRateDto);
        mockMvc
                .perform(
                        post(RATE_URL)
                                .header("Authorization", JWT)
                                .contentType(MediaType.APPLICATION_JSON).content(userRateDtoJsonString))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_code").value(4001))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestAlertException));
    }

    @Test
    void shouldReturnUserRate_whenRateMovieIsCalled() throws Exception {
        long databaseSizeBeforeCreate = userRateRepository.count();
        userRate.setRate(5);
        userRate.setTitle("Lost in Translation"); // Its a new Movie
        // Create the UserRate
        UserRateDto userRateDto = userRateMapper.toDto(userRate);
        Gson gson = new Gson();
        String userRateDtoJsonString = gson.toJson(userRateDto);

        mockMvc
                .perform(
                        post(RATE_URL)
                                .header("Authorization", JWT)
                                .contentType(MediaType.APPLICATION_JSON).content(userRateDtoJsonString))
                .andExpect(status().isCreated() )
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].title").value("Lost in Translation"));

        // Validate the UserRate in the database
        long databaseSizeAfterCreate = userRateRepository.count();
        assertEquals(databaseSizeBeforeCreate + 1, databaseSizeAfterCreate);
    }

}

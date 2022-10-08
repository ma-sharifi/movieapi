package com.example.movieapi.controller;

import com.example.movieapi.MovieapiApplication;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.repository.UserRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

//@IntegrationTest
@SpringBootTest(classes = MovieapiApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerTest {

    private static final String DEFAULT_TITLE = "Black Swan";
    private static final String ENTITY_API_URL = "/v1/movies";
    private static final String WON_URL = ENTITY_API_URL+"/won";
    private static final String RATE_URL = ENTITY_API_URL+"/rate";
    private static final String TOP_TEN_URL = ENTITY_API_URL+"/top-ten";

    @Autowired
    private UserRateRepository userRateRepository;

    @Autowired
    private MockMvc mockMvc;

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
//
    @Test
    void shouldReturnMovieInfo_whenMovieWonOscarIsCalledByTitle() throws Exception {
        mockMvc
                .perform(
                        get(WON_URL+"?title=The Hurt Locker"))//Black Swan
//                                .header("Authorization", JWT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Title").value("The Hurt Locker"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Year").value("2008"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].imdbID").value("tt0887912"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Director").value("Kathryn Bigelow"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Awards").value("Won 6 Oscars. 125 wins & 130 nominations total"));
    }

//    @Test TODO
//    public void givenNullTitle_thenReturnValidationErrors() throws Exception {
//        ApiErrorDto error = ApiErrorDto.builder().reason(Reason.REQUIRED_FIELD).param("title").message("Param is missing").build();
//        ApiResponseDto apiResponseDto = ApiResponseDto.builder().errors(error).badRequest().build();
//
//        mockMvc.perform(get(WON_URL).header(AUTHORIZATION_HEADER, createJwtHeader(USER_1))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(asJsonString(apiResponseDto)));
//    }

//    @Test
//    void givenMovieTitle_ShouldReturnOmdbApiException() throws Exception {
//        mockMvc
//                .perform(
//                        post(ENTITY_API_URL + "/won?title=Black Swan"))
//                .andExpect(status().isInternalServerError())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof OmdbApiException));
//    }

    @Test
    void shouldReturnThrowNotFoundException_whenNotWon() throws Exception {
        mockMvc
                .perform(
                        get(WON_URL + "/won?title=Black Swan"))
//                                .header("Authorization", JWT))
                .andExpect(status().isNotFound());
        // TODO
    }
//
////    @Test
////    void givenMovieTitle_ShouldReturnNotWon(){
//////        isWonOscar(String title)
////    }
//
//    @Test
//    void shouldReturnTopTenMove() throws Exception {
//    }

//    @Test
//    void shouldRateMovie() throws Exception {
//        UserRate entity = new UserRate();
//        entity.setTitle("Mahdi");
//        entity.setTitle("Black Swan");
//        entity.setId(new UserMovieId("tt1375666","mahdi"));
//
//        userRateRepository.save(entity); //Save UserRate;
//
//        long count = userRateRepository.count();
//        // Open an Account for existing customer
//        mockMvc
//                .perform(
//                        post( ENTITY_API_URL + "/Black Swan"))
////                                .header("X-Request-Id", X_REQUEST_ID))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.Title").value("Black Swan"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.Year").value("2010"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbID").value("tt0947798"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.Director").value("Darren Aronofsky"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.Awards").value("Won 1 Oscar. 97 wins & 280 nominations total"));
//
//
////        // Validate the Accounts in the database
////        List<Account> accountsList = accountsRepository.findAll();
////        assertThat(accountsList).hasSize(databaseSizeBeforeCreate + 1);
////        Account testAccount = accountsList.get(accountsList.size() - 1);
////        assertThat(testAccount.getBalance()).isEqualByComparingTo(DEFAULT_BALANCE);
//    }

    /*
     {
            "imdbRating": "7.5",
            "imdbVotes": "450,027",
            "imdbID": "tt0887912",
            "Title": "The Hurt Locker",
            "Year": "2008",
            "Rated": "R",
            "Released": "31 Jul 2009",
            "Runtime": "131 min",
            "Genre": "Drama, Thriller, War",
            "Director": "Kathryn Bigelow",
            "Writer": "Mark Boal",
            "Actors": "Jeremy Renner, Anthony Mackie, Brian Geraghty",
            "Plot": "During the Iraq War, a Sergeant recently assigned to an army bomb squad is put at odds with his squad mates due to his maverick way of handling his work.",
            "Language": "English, Arabic",
            "Country": "United States",
            "Awards": "Won 6 Oscars. 125 wins & 130 nominations total",
            "Poster": "https://m.media-amazon.com/images/M/MV5BYWYxZjU2MmQtMmMzYi00ZWUwLTg2ZWQtMDExZTVlYjM3ZWM1XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
            "Ratings": [
                {
                    "Source": "Internet Movie Database",
                    "Value": "7.5/10"
                },
                {
                    "Source": "Rotten Tomatoes",
                    "Value": "97%"
                },
                {
                    "Source": "Metacritic",
                    "Value": "95/100"
                }
            ],
            "Metascore": "95",
            "Type": "movie",
            "DVD": "12 Jan 2010",
            "BoxOffice": "$17,017,811",
            "Production": "N/A",
            "Website": "N/A",
            "Response": "True"
        }

    {
  "Title": "Black Swan",
  "Year": "2010",
  "Rated": "R",
  "Released": "17 Dec 2010",
  "Runtime": "108 min",
  "Genre": "Drama, Thriller",
  "Director": "Darren Aronofsky",
  "Writer": "Mark Heyman, Andres Heinz, John J. McLaughlin",
  "Actors": "Natalie Portman, Mila Kunis, Vincent Cassel",
  "Plot": "A committed dancer struggles to maintain her sanity after winning the lead role in a production of Tchaikovsky's \"Swan Lake\".",
  "Language": "English, French, Italian",
  "Country": "United States",
  "Awards": "Won 1 Oscar. 97 wins & 280 nominations total",
  "Poster": "https://m.media-amazon.com/images/M/MV5BNzY2NzI4OTE5MF5BMl5BanBnXkFtZTcwMjMyNDY4Mw@@._V1_SX300.jpg",
  "Ratings": [
    {
      "Source": "Internet Movie Database",
      "Value": "8.0/10"
    },
    {
      "Source": "Rotten Tomatoes",
      "Value": "85%"
    },
    {
      "Source": "Metacritic",
      "Value": "79/100"
    }
  ],
  "Metascore": "79",
  "imdbRating": "8.0",
  "imdbVotes": "759,022",
  "imdbID": "tt0947798",
  "Type": "movie",
  "DVD": "29 Mar 2011",
  "BoxOffice": "$106,954,678",
  "Production": "N/A",
  "Website": "N/A",
  "Response": "True"
}
     */
}

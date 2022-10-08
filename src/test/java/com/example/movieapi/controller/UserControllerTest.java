package com.example.movieapi.controller;

import com.example.movieapi.MovieapiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */
@SpringBootTest(classes = MovieapiApplication.class)
@AutoConfigureMockMvc
 class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

//   @WithMockUser(value = "spring")
//   @Test
//    void shouldReturnTokenAndUse_whenLoginIsCalled() throws Exception {
//       mockMvc.perform(post("/login?username=mahdi"))//.user("username", "password")
//                        .acceptMediaType(MediaType.APPLICATION_JSON)
//                .andExpect(status().isOk());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/login")
//                .param("username", "mahdi")
//                .param("password", "fieldValue")
//                )
//                .andExpect(status().isOk());
//
//    }
//    @Test
//    void shouldReturnMovieInfo_whenMovieWonOscarIsCalledByTitle() throws Exception {
//        mockMvc
//                .perform(
//                        get(WON_URL+"?title=The Hurt Locker"))//Black Swan
////                                .header("Authorization", JWT))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Title").value("The Hurt Locker"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Year").value("2008"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].imdbID").value("tt0887912"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Director").value("Kathryn Bigelow"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].Awards").value("Won 6 Oscars. 125 wins & 130 nominations total"));
//    }

//    @Test
//    public void whenUserAccessWithWrongCredentialsWithDelegatedEntryPoint_shouldFail() throws Exception {
//        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), "Authentication failed");
//        mvc.perform(formLogin("/login").user("username", "admin")
//                        .password("password", "wrong")
//                        .acceptMediaType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.errorMessage", is(re.getErrorMessage())));
//    }
}

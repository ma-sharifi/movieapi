package com.example.controller;

import com.example.IntegrationTest;
import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.repository.UserRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@IntegrationTest
public class MovieControllerTest {

    private static final String DEFAULT_TITLE = "Black Swan";

    private static final String ENTITY_API_URL = "/v1/movie";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

//    @Autowired
//    private AccountRepository accountsRepository;
//
    @Autowired
    private UserRateRepository userRateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private MockRestServiceServer mockServiceServer;

    private UserRate userRate;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRate createEntity(EntityManager em) {
        UserRate userRate = new UserRate();
        userRate.setTitle(DEFAULT_TITLE);
        return userRate;
    }

    @BeforeEach
    public void initTest() {
        userRate = createEntity(em);
    }

    @Test
    @Transactional
    void shouldRateMovie() throws Exception {
        UserRate entity = new UserRate();
        entity.setTitle("Mahdi");
        entity.setTitle("Black Swan");
        entity.setId(new UserMovieId("tt1375666","mahdi"));

        userRateRepository.save(entity); //Save UserRate;
//
//        long count = userRateRepository.count();
//        // Open an Account for existing customer
//        mockMvc
//                .perform(
//                        post( ENTITY_API_URL + "/" + customer.getId() + "/accounts")
//                                .header("Initial-Credit", DEFAULT_BALANCE)
//                                .header("X-Request-Id", X_REQUEST_ID))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(DEFAULT_BALANCE));
//
//        // Validate the Accounts in the database
//        List<Account> accountsList = accountsRepository.findAll();
//        assertThat(accountsList).hasSize(databaseSizeBeforeCreate + 1);
//        Account testAccount = accountsList.get(accountsList.size() - 1);
//        assertThat(testAccount.getBalance()).isEqualByComparingTo(DEFAULT_BALANCE);
    }
}

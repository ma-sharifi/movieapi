package com.example.movieapi.repository;

import com.example.movieapi.MovieapiApplication;
import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.GeneralMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */
@SpringBootTest(classes = MovieapiApplication.class)
class UserRateRepositoryTest {

    @Autowired
    private UserRateRepository userRateRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    void shouldSaveAndLoad() {
        //The first saves a newly created UserRate in the database
        UserMovieId id = transactionTemplate.execute((ts) -> {
            UserRate userRate = new UserRate(new UserMovieId("tt1375666","mahdi"),9,"Inception",1L);
            userRateRepository.save(userRate);
            return userRate.getId();
        });
        //The second transaction loads the UserRate and verifies that its fields are properly initialized
        transactionTemplate.execute((ts) -> {
            UserRate userRate = userRateRepository.findById(id).get();
            assertEquals("tt1375666", userRate.getId().getImdbId());
            assertEquals("mahdi", userRate.getId().getUsername());
            assertEquals(1L, userRate.getBoxOffice());

            return null;
        });
    }
    @Test
    void findTopTenOrderedByBoxOffice() {
        //The first saves a newly created UserRate in the database with rate more than 10
        UserMovieId id = transactionTemplate.execute((ts) -> {
            UserRate userRate = new UserRate(new UserMovieId("tt1375666","mahdi"),20,"Inception",1000000000L);
            userRateRepository.save(userRate);
            return userRate.getId();
        });
        //The second transaction loads the UserRate and verifies that its fields are properly initialized
        transactionTemplate.execute((ts) -> {
            List<Object[]> list = userRateRepository.findTopTenOrderedByBoxOffice();
            List<UserRateDto> userRateDtoList = list.stream().map(GeneralMapper::toUserRateDto).collect(Collectors.toList());
            assertNotNull(userRateDtoList);

            assertEquals("tt1375666", userRateDtoList.get(0).getImdbId());
            assertEquals("Inception", userRateDtoList.get(0).getTitle());
            assertEquals(BigInteger.valueOf(1000000000L), userRateDtoList.get(0).getBoxOffice());

            return null;
        });
    }

}
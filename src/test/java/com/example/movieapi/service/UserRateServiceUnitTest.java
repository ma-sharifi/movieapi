package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */
@ExtendWith(MockitoExtension.class)
class UserRateServiceUnitTest {

    UserRateService userRateService;

    @Mock
    UserRateRepository userRateRepository;

    @Spy
    UserRateMapper userRateMapper = Mappers.getMapper(UserRateMapper.class);

    @BeforeEach
    void initializeService() {
        userRateService = new UserRateService(userRateRepository, userRateMapper);
    }

    @Test
    void shouldReturnUserRate_whenSaveRateIsCalled() throws Exception {
        UserMovieId id = new UserMovieId("tt1375666", "mahdi");
        UserRate userRate = new UserRate(id, 1, "Inception", 92587330L);
        UserRateDto userRateDto= userRateMapper.toDto(userRate);
         // Arrange
        when(userRateRepository.save(any(UserRate.class))).thenReturn(userRate);

        // Act
        UserRateDto savedUsedRateDto = userRateService.save(userRate);

        // Assert
        assertThat(userRateDto, is(equalTo(savedUsedRateDto)));
    }

    @Test
    void shouldReturnMovieNotFoundException_whenSaveRateIsCalled() throws Exception {
        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            // Act
            userRateService.findOne(new UserMovieId("1","1"));
        });
        // Assert
        assertTrue(thrown.getMessage().contains("movie with id"));
    }

//    @Test
//    void shouldReturnUserRate_whenTopTenCalled1() throws Exception {
//
//        // Arrange
//        UserMovieId id = new UserMovieId("tt1375666", "mahdi");
//        UserRate userRate = new UserRate(id, 1, "Inception", 92587330L);
//        UserRateDto userRateDto = UserRateDto.builder().imdbId("tt1375666").username("mahdi").boxOffice(92587330L).title("Inception").build();
//        when(userRateRepository.save(any(UserRate.class))).thenReturn(userRate);
//        when(userRateMapper.toDto(any(UserRate.class))).thenReturn(userRateDto);
//        System.out.println("#userRate: "+userRate);
//        System.out.println("#userRateDto: "+userRateDto);
////        when(userRateMapper.toDto(any(UserRate.class))).thenReturn(n);
//
//        // Act
//        UserRateDto retrievedUserRateDto = userRateService.findOne(id);
//        // Assert
//        assertThat(retrievedUserRateDto, is(equalTo(userRateMapper.toDto(userRate))));
//    }

}
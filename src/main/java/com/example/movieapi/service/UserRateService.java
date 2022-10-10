package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.GeneralMapper;
import com.example.movieapi.service.mapper.UserRateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */
@Service
@Slf4j
public class UserRateService {

    UserRateRepository repository;
    UserRateMapper userRateMapper;

    @Value("${server.port}")
    private String port;

    public UserRateService(UserRateRepository repository, UserRateMapper userRateMapper) {
        this.repository = repository;
        this.userRateMapper = userRateMapper;
    }

    public UserRateDto save(UserRate userRate) {
        return userRateMapper.toDto(repository.save(userRate));
    }

    public UserRateDto findOne(UserMovieId id) {
        Optional<UserRate> userRateOptional = repository.findById(id);
        return userRateOptional.map(userRateMapper::toDto).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public List<UserRateDto> findTopTenOrderedByBoxOffice() {
        List<Object[]> list = repository.findTopTenOrderedByBoxOffice();
        List<UserRateDto> userRateDtoList = list.stream().map(GeneralMapper::toUserRateDto).collect(Collectors.toList());
        for (UserRateDto dto : userRateDtoList)
            dto.setUrl("http://localhost:" + port + "/v1/movies/" + dto.getImdbId());
        return userRateDtoList;
    }
    
}

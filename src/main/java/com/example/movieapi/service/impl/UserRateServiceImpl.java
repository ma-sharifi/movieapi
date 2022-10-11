package com.example.movieapi.service.impl;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.UserRateService;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.GeneralMapper;
import com.example.movieapi.service.mapper.UserRateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */
@Service
@Slf4j
public class UserRateServiceImpl implements UserRateService {

    private final UserRateRepository repository;
    private final UserRateMapper userRateMapper;

    @Value("${server.port}")
    private String port;

    public UserRateServiceImpl(UserRateRepository repository, UserRateMapper userRateMapper) {
        this.repository = repository;
        this.userRateMapper = userRateMapper;
    }

    @Override
    public UserRateDto save(UserRate userRate) {
        return userRateMapper.toDto(repository.save(userRate));
    }

    @Override
    public UserRateDto findOne(UserMovieId id) {
        Optional<UserRate> userRateOptional = repository.findById(id);
        return userRateOptional.map(userRateMapper::toDto).orElseThrow(() -> new MovieNotFoundException(id.getImdbId()));
    }

    @Override
    public List<UserRateDto> findTopTenOrderedByBoxOffice() {
        List<Object[]> list = repository.findTopTenOrderedByBoxOffice();
        List<UserRateDto> userRateDtoList = list.stream().map(GeneralMapper::toUserRateDto).collect(Collectors.toList());
        for (UserRateDto dto : userRateDtoList)
            dto.setUrl("http://localhost:" + port + "/v1/movies/" + dto.getImdbId());
        return userRateDtoList;
    }
    
}

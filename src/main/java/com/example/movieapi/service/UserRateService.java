package com.example.movieapi.service;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */
@Service
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
        List<Object[]> list = repository.findTopTenOrderedByBoxOffice(PageRequest.of(0, 10));
        List<UserRateDto> userRateDtoList = list.stream().map(this::toUserRateDto).collect(Collectors.toList());
        for (UserRateDto dto : userRateDtoList)
            dto.setUrl("http://localhost:" + port + "/v1/movies/" + dto.getImdbId());
        return userRateDtoList;
    }

    private UserRateDto toUserRateDto(Object[] userRateRetreive) {
        return UserRateDto.builder()
                .imdbId((String) userRateRetreive[0])
                .title((String) userRateRetreive[1])
                .boxOffice((long) userRateRetreive[2])
                .rateAverage((double) userRateRetreive[3])
                .rate((int) userRateRetreive[4])
                .username((String) userRateRetreive[5])
                .build();
    }
}

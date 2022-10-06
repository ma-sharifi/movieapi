package com.example.movieapi.service.impl;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.OscarWinnerCsvService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RateDto;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    OmdbService omdbService;
    OscarWinnerCsvService winnerCsvService;
    UserRateRepository repository;
    UserRateMapper userRateMapper;

    public MovieServiceImpl(OmdbService omdbService, OscarWinnerCsvService winnerCsvService, UserRateRepository repository, UserRateMapper userRateMapper) {
        this.omdbService = omdbService;
        this.winnerCsvService = winnerCsvService;
        this.repository = repository;
        this.userRateMapper = userRateMapper;
    }

    @Override
    public OmdbResponseDto isWonOscar(String title) {
        boolean isWon = winnerCsvService.isWonByTitleForBestPicture(title);
        if (isWon) {
            // look up movie on API
            Optional<OmdbResponseDto> responseDtoOptional = omdbService.getSingleMovieByTitle(title);
            if (responseDtoOptional.isPresent())
                return responseDtoOptional.get();
        }
        return null;
    }

    @Override
    public OmdbResponseDto rateByTitle(UserRateDto rateRequestDto, String user) {
        return null;
    }

    @Override
    public UserRateDto rateByImdbId(String imdbId, int rate, String user) {
//        String box="$106,954,678";
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByImdbId(imdbId);
        UserRate userRate = null;
        if (omdbResponseDtoOptional.isPresent()) {
            OmdbResponseDto omdbResponseDto = omdbResponseDtoOptional.get();
            long boxOffice = Long.parseLong(omdbResponseDto.getBoxOffice().replace("$", "").replace(",", ""));
            UserMovieId id=new UserMovieId(imdbId, user);
            userRate = UserRate.builder()
                    .rate(rate)
                    .id(id)
                    .title(omdbResponseDto.getTitle())
                    .boxOffice(boxOffice).build();
            repository.save(userRate);
        }
        return userRateMapper.toDto(userRate);
    }

    @Override
    public List<OmdbResponseDto> findTopTen() { //tt1375666 , tt0947798
        List<OmdbResponseDto> result=new ArrayList<>();
        List<Object[]> list = repository.findTopTenOrderedByBoxOffice(PageRequest.of(0, 10));
        List<UserRateDto> userRateDtoList = list.stream().map(this::toUserRateDto).toList();
        log.info("#topRateMovies: " + userRateDtoList);

        for (UserRateDto userRateDto: userRateDtoList){
            Optional<OmdbResponseDto> omdbResponseDtoOptional=  omdbService.getSingleMovieByImdbId(userRateDto.getImdbId());
            if(omdbResponseDtoOptional.isPresent()){
                OmdbResponseDto omdbResponseDto= omdbResponseDtoOptional.get();
                omdbResponseDto.addRate(new RateDto(userRateDto.getUsername(),userRateDto.getRate()+"/"+10));
                result.add(omdbResponseDto);
            }
        }
        return result;
    }

    public UserRateDto toUserRateDto(Object[] record) {
        UserRateDto userRateDto = new UserRateDto();
        userRateDto.setImdbId((String) record[0]);
        userRateDto.setTitle((String) record[1]);
        userRateDto.setBoxOffice((long) record[2]);
        userRateDto.setRateAverage((double) record[3]);
        userRateDto.setRate((int) record[4]);
        userRateDto.setUsername((String) record[5]);
        return userRateDto;
    }

}

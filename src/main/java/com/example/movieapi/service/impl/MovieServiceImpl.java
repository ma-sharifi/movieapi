package com.example.movieapi.service.impl;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.OscarWinnerCsvService;
import com.example.movieapi.service.UserRateService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RateDto;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    OmdbService omdbService;
    OscarWinnerCsvService winnerCsvService;
    UserRateService userRateService;
    UserRateMapper  userRateMapper;

    @Value("${server.port}")
    private String port;


    public MovieServiceImpl(OmdbService omdbService, OscarWinnerCsvService winnerCsvService, UserRateService userRateService, UserRateMapper userRateMapper) {
        this.omdbService = omdbService;
        this.winnerCsvService = winnerCsvService;
        this.userRateService = userRateService;
        this.userRateMapper = userRateMapper;
    }

    /**
     * Because there is no information about type of Oscar on Omdb API,
     * First of all SCV file search.
     * @param title
     * @return
     */
    @Override
    public OmdbResponseDto  isWonOscar(String title) throws MovieNotFoundException {
        Optional<OmdbResponseDto>  responseDtoOptional = Optional.empty();
        boolean isWon = winnerCsvService.isWonByTitleForBestPicture(title);
        if (isWon) {
            // look up movie on API
            responseDtoOptional = omdbService.getSingleMovieByTitle(title);
            responseDtoOptional.ifPresent(movie->movie.setWonBestPicture(true));
        }
        return responseDtoOptional.orElseThrow(()->new MovieNotFoundException(title));
    }

    @Override
    public UserRateDto rateByTitle(String title, int rate, String user) throws MovieNotFoundException{
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByTitle(title);
        UserRate userRate = null;
        UserRateDto result= null;
        if (omdbResponseDtoOptional.isPresent()) {
            OmdbResponseDto omdbResponseDto = omdbResponseDtoOptional.get();
            long boxOffice = Long.parseLong(omdbResponseDto.getBoxOffice().replace("$", "").replace(",", ""));
            UserMovieId id=new UserMovieId(omdbResponseDto.getImdbID(), user);
            userRate = UserRate.builder()
                    .rate(rate)
                    .id(id)
                    .title(omdbResponseDto.getTitle())
                    .boxOffice(boxOffice).build();
            userRateService.save(userRate);
            result= userRateMapper.toDto(userRate);
            result.setUrl("http://localhost:" + port + "/v1/movies/" + omdbResponseDto.getImdbID());
            return result;

        } else throw new MovieNotFoundException(title);

    }
//
//    @Override
//    public UserRateDto rateByImdbId(String imdbId, int rate, String user) { //TODO Remove It
////        String box="$106,954,678";
//        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByImdbId(imdbId);
//        UserRateDto userRateDto=null;
//        UserRate userRate = null;
//        if (omdbResponseDtoOptional.isPresent()) {
//            OmdbResponseDto omdbResponseDto = omdbResponseDtoOptional.get();
//            long boxOffice = Long.parseLong(omdbResponseDto.getBoxOffice().replace("$", "").replace(",", ""));
//            UserMovieId id=new UserMovieId(omdbResponseDto.getImdbID(), user);
//            userRate = UserRate.builder()
//                    .rate(rate)
//                    .id(id)
//                    .title(omdbResponseDto.getTitle())
//                    .boxOffice(boxOffice).build();
//            userRateDto= userRateService.save(userRate);
//        }
//        return userRateDto;
//    }

    @Override
    public List<UserRateDto> findTopTen() { //tt1375666 , tt0947798
        log.debug("#findTopTen called.");
        List<UserRateDto> userRateDtoList = userRateService.findTopTenOrderedByBoxOffice();
        return userRateDtoList;
    }

}

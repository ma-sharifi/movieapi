package com.example.movieapi.controller.impl;

import com.example.movieapi.controller.MovieController;
import com.example.movieapi.security.AuthenticationFacade;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.impl.OmdbServiceImpl;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.ResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@RestController
@RequestMapping("/v1/movies")
@Tag(name = "movie-controller for handling movie requests", description = "isWon,get,rate,top ten the movie")
@SecurityRequirement(name = "JWTtoken")
@Slf4j
public class MovieControllerImpl implements MovieController {

    private final OmdbServiceImpl omdbService;
    private final MovieService movieService;
    private final AuthenticationFacade authenticationFacade;

    public MovieControllerImpl(OmdbServiceImpl omdbService, MovieService movieService, AuthenticationFacade authenticationFacade) {
        this.omdbService = omdbService;
        this.movieService = movieService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    @GetMapping(value = "/won", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< ResponseDto<OmdbResponseDto>> getMovieWonOscar(
            @Valid @RequestParam("title") @NotNull(message = "#title is required") String title) {
        log.debug("#call getMovieWonOscar title: " + title);
        OmdbResponseDto omdbResponseDto = movieService.isWonOscar(title);
        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto<>(List.of(omdbResponseDto));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< ResponseDto<UserRateDto>> rateByTitle(@Valid @RequestBody UserRateDto userRateDto) {
        log.debug("#rateByTitle is called. userRateDto: "+userRateDto);
        ResponseDto<UserRateDto> responseDto = new ResponseDto<>();
        UserRateDto userRate = movieService.rateByTitle(userRateDto.getTitle(), userRateDto.getRate(), authenticationFacade.getAuthentication().getName());
        if (userRate != null)
            responseDto.setPayload(List.of(userRate));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(value = "/top-ten") //
    public ResponseEntity<ResponseDto<UserRateDto>> getTopTenMove() {
        ResponseDto<UserRateDto> responseDto = new ResponseDto<>();
        responseDto.setPayload(movieService.findTopTen());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping(value = "{imdb-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< ResponseDto<OmdbResponseDto>> getMovieFromOmdbApi(
            @Valid @PathVariable("imdb-id") String imdbId) {//tt1375666 , tt0947798
        log.debug("#call getMovieByFromOmdbApi imdbId: " + imdbId);
        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto<>();
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByImdbId(imdbId);
        omdbResponseDtoOptional.ifPresent(omdbResponseDto -> responseDto.setPayload(List.of(omdbResponseDto)));
        return ResponseEntity.ok(responseDto);
    }

}

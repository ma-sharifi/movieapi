package com.example.movieapi.controller;

import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.OscarWinnerCsvService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RequestDto;
import com.example.movieapi.service.dto.ResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@RestController
@RequestMapping("/v1/movies")
@Tag(name = "movie-controller for handling movie requests", description = "get,rate,top ten the movie")
@Slf4j
public class MovieController {

    private final OscarWinnerCsvService oscarWinnerService;
    private final OmdbService omdbService;
    private final MovieService movieService;

    public MovieController(OscarWinnerCsvService oscarWinnerService, OmdbService omdbService, MovieService movieService) {
        this.oscarWinnerService = oscarWinnerService;
        this.omdbService = omdbService;
        this.movieService = movieService;
    }

//    @GetMapping(value = "o", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ResponseDto> callOmdb() {
//        log.info("#Call callOmdb");
//        List<OmdbResponseDto> payload = new ArrayList<>();
//        OmdbResponseDto omdbResponseDto = omdbService.getSingleMovieByTitle("Inception");
//        payload.add(omdbResponseDto);
//        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto<>(payload);
//        responseDto.setPayload(payload);
//        return ResponseEntity.ok(responseDto);
//    }

    @GetMapping(value = "/won", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getMovieWonOscar(
            @Valid @RequestParam("title") @NotNull(message = "#title is required") String title) {
        log.info("#call getMovieWonOscar title: " + title);
        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto();
        OmdbResponseDto result = movieService.isWonOscar(title);
        if (result != null)
            responseDto.setPayload(List.of(result));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "id/{imdb-id}/rate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> rateByImdbId(
            @PathVariable("imdb-id") String imdbId,
            @Valid @RequestBody RequestDto requestDto) {
        ResponseDto<UserRateDto> responseDto = new ResponseDto();
        UserRateDto userRate = movieService.rateByImdbId(imdbId, requestDto.getRate(), "mahdi");
        if (userRate != null)
            responseDto.setPayload(List.of(userRate));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> rateByTitle(@Valid @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(value = "/top-ten") // tt1375666 , tt0947798
    public ResponseEntity<ResponseDto<OmdbResponseDto>> getTopTenMove() {
        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto();
        responseDto.setPayload(movieService.findTopTen());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

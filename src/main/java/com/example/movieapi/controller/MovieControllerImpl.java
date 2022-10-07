package com.example.movieapi.controller;

import com.example.movieapi.service.MovieService;
import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.OscarWinnerCsvService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RequestDto;
import com.example.movieapi.service.dto.ResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "movie-controller for handling movie requests", description = "get,rate,top ten the movie")
@Slf4j
public class MovieControllerImpl implements MovieController {

    private final OscarWinnerCsvService oscarWinnerService;
    private final OmdbService omdbService;
    private final MovieService movieService;

    public MovieControllerImpl(OscarWinnerCsvService oscarWinnerService, OmdbService omdbService, MovieService movieService) {
        this.oscarWinnerService = oscarWinnerService;
        this.omdbService = omdbService;
        this.movieService = movieService;
    }

    @Override
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

    @GetMapping(value = "id/{imdb-id}/rate", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get top ten rated movies ordered by boxoffice",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @Operation(summary = "return top ten rated movies ordered by boxoffice")
    @GetMapping(value = "/top-ten") //
    public ResponseEntity<ResponseDto<UserRateDto>> getTopTenMove() { //TODO Completable future.
        ResponseDto<UserRateDto> responseDto = new ResponseDto();
        responseDto.setPayload(movieService.findTopTen());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping(value = "{imdb-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getMovieByFromOmdbApi(
            @Valid @PathVariable("imdb-id") String imdbId){//tt1375666 , tt0947798
        log.info("#call getMovieByFromOmdbApi imdbId: " + imdbId);
        ResponseDto<OmdbResponseDto> responseDto = new ResponseDto();
        Optional<OmdbResponseDto> omdbResponseDtoOptional = omdbService.getSingleMovieByImdbId(imdbId);
        omdbResponseDtoOptional.ifPresent(omdbResponseDto -> responseDto.setPayload(List.of(omdbResponseDto)));
        return ResponseEntity.ok(responseDto);
    }

}

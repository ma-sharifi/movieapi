package com.example.movieapi.controller;

import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.OscarWinnerService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RequestDto;
import com.example.movieapi.service.dto.ResponseDto;
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

    private final OscarWinnerService oscarWinnerService;
    private final OmdbService omdbService;

    public MovieController(OscarWinnerService oscarWinnerService, OmdbService omdbService) {
        this.oscarWinnerService = oscarWinnerService;
        this.omdbService = omdbService;
    }

    @GetMapping(value = "o", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> callOmdb(){
        log.info("#Call callOmdb");
        List<OmdbResponseDto> payload= new ArrayList<>();
        OmdbResponseDto omdbResponseDto=omdbService.getSingleMovie("Inception");
        payload.add(omdbResponseDto);
        ResponseDto<OmdbResponseDto> responseDto=new ResponseDto<>(payload);
        responseDto.setPayload(payload);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> search(
            @Valid @RequestParam("title") @NotNull(message = "title is required") String title) {
        ResponseDto responseDto = new ResponseDto();
        oscarWinnerService.findByTitle(title);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> rateMove(@Valid @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(value = "/top-ten")
    public ResponseEntity<ResponseDto> getTopTenMove(@Valid
                                                 @RequestParam(value = "page", defaultValue = "0")
                                                 @Range(min = 1, max = 10, message = "Invalid range for page param")
                                                         Integer page) {
        ResponseDto responseDto = new ResponseDto();
        return ResponseEntity.ok(responseDto);
    }
}

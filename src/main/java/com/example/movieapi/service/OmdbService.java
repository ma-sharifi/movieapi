package com.example.movieapi.service;

import com.example.movieapi.exception.OmdbApiException;
import com.example.movieapi.service.dto.OmdbResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Service
@Slf4j
public class OmdbService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${omdbapi.url}")
    private String url;

    @Value("${omdbapi.apikey}")
    private String apiKey;

    public Optional<OmdbResponseDto> getSingleMovieByTitle(String title) {
        Optional<OmdbResponseDto> dtoOptional;
        try {
            dtoOptional=Optional.ofNullable(restTemplate.getForEntity(url + "/?t={title}&apiKey={apiKey}", OmdbResponseDto.class, title, apiKey).getBody());
        } catch (Exception ex) {
            throw new OmdbApiException();
        }
        return dtoOptional;
    }

    public Optional<OmdbResponseDto> getSingleMovieByImdbId(String imdbId) {
        Optional<OmdbResponseDto> dtoOptional;
        try {
            dtoOptional= Optional.ofNullable(restTemplate.getForEntity(url + "/?i={imdbId}&apiKey={apiKey}", OmdbResponseDto.class, imdbId, apiKey).getBody());
        } catch (Exception ex) {
            throw new OmdbApiException();
        }
        return dtoOptional;
    }


}

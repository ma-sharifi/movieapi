package com.example.movieapi.service.impl;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.exception.OmdbApiException;
import com.example.movieapi.service.OmdbService;
import com.example.movieapi.service.dto.OmdbResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Service
@Slf4j
public class OmdbServiceImpl implements OmdbService {

    private final RestTemplate restTemplate;

    public OmdbServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${omdbapi.url}")
    private String url;

    @Value("${omdbapi.apikey}")
    private String apiKey;

    @Override
    public Optional<OmdbResponseDto> getSingleMovieByTitle(String title) throws MovieNotFoundException {
        return callOmdbServer(url + "/?t={title}&apiKey={apiKey}", title);
    }

    @Override
    public Optional<OmdbResponseDto> getSingleMovieByImdbId(String imdbId) {
        return callOmdbServer(url + "/?i={imdbId}&apiKey={apiKey}", imdbId);
    }

    private Optional<OmdbResponseDto> callOmdbServer(String url, String search) throws MovieNotFoundException {
        Optional<OmdbResponseDto> dtoOptional;
        OmdbResponseDto omdbResponseDto = null;
        try {
            omdbResponseDto = restTemplate.getForEntity(url, OmdbResponseDto.class, search, apiKey).getBody();
        } catch (Exception ex) {
            throw new OmdbApiException(" Omdb Call! " + ex.getMessage()); //"False"  // "Response": "True"  "Error": "Movie not found!"
        }
        if (omdbResponseDto != null) {
            if ("True".equalsIgnoreCase(omdbResponseDto.getResponse()))
                dtoOptional = Optional.of(omdbResponseDto);
            else
                throw new MovieNotFoundException(search+" ;Based on Omdb Call!" );
        } else throw new OmdbApiException("The response of our call is null. Omdb Call!");

        return dtoOptional;
    }

}

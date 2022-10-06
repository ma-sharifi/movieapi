package com.example.movieapi.service;

import com.example.movieapi.service.dto.OmdbResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Service
public class OmdbService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${omdbapi.url}")
    private String url;

    @Value("${omdbapi.apikey}")
    private String apiKey;

    public  Optional<OmdbResponseDto> getSingleMovieByTitle(String title) {
        return Optional.ofNullable(restTemplate.getForEntity(url + "/?t={title}&apiKey={apiKey}", OmdbResponseDto.class, title, apiKey).getBody());
    }
    public Optional<OmdbResponseDto> getSingleMovieByImdbId(String imdbId) {
        return Optional.ofNullable(restTemplate.getForEntity(url + "/?i={imdbId}&apiKey={apiKey}", OmdbResponseDto.class, imdbId, apiKey).getBody());
    }



}

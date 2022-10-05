package com.example.movieapi.service;

import com.example.movieapi.service.dto.OmdbResponseDto;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


    public OmdbResponseDto getSingleMovie(String title) {
        return restTemplate.getForEntity(url + "/?t={title}&apiKey={apiKey}", OmdbResponseDto.class, title, apiKey).getBody();
    }

}

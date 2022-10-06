package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */

public enum MovieType {
    @JsonProperty("movie")
    MOVIE,
    @JsonProperty("series")
    SERIES,
    @JsonProperty("episode")
    EPISODE
}

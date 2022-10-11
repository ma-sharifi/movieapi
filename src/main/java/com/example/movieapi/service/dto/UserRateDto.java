package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 *
 * https://www.omdbapi.com/?t=swan&apikey=c732beed
 * {
 * "Source": "Internet Movie Database",
 * "Value": "8.0/10"
 * },
 */
@Getter
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRateDto {

    @JsonProperty("imdb_id")
    private String imdbId;//tt1375666

    private String username;

    private Integer rate;

    @JsonProperty("rate_average")
    private Double rateAverage;

    private String title;

    @JsonProperty("box_office")
    private Long boxOffice;

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 * <p>
 * https://www.omdbapi.com/?t=swan&apikey=c732beed
 * {
 * "Source": "Internet Movie Database",
 * "Value": "8.0/10"
 * },
 */
@Data
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
}

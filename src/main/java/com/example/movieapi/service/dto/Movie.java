package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mapstruct.control.MappingControl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    @JsonProperty("Title")
    public String title;

    @JsonProperty("Year")
    public String year;

    @JsonProperty("Rated")
    public String rated;

    @JsonProperty("Released")
    public String released;

    @JsonProperty("Runtime")
    public String runtime;

    @JsonProperty("Genre")
    public String genre;

    @JsonProperty("Director")
    public String director;

    @JsonProperty("Writer")
    public String writer;

    @JsonProperty("Actors")
    public String actors;

    @JsonProperty("Plot")
    public String plot;

    @JsonProperty("Language")
    public String language;

    @JsonProperty("Country")
    public String country;

    @JsonProperty("Awards")
    public String awards;

    @JsonProperty("Poster")
    public String poster;

    @JsonProperty("Ratings")
    public List<RateDto> ratings;

    @JsonProperty("Metascore")
    public String metascore;

    public String imdbRating;
    public String imdbVotes;
    public String imdbID;

    @JsonProperty("Type")
    public String type;

    @JsonProperty("DVD")
    public String dVD;

    @JsonProperty("BoxOffice")
    public String boxOffice;

    @JsonProperty("Production")
    public String production;

    @JsonProperty("Website")
    public String website;

    @JsonProperty("Response")
    public String response;

    @JsonProperty("Error")
    private String error;

    private Long id;
    private String metaScore;
    private Boolean won;


}

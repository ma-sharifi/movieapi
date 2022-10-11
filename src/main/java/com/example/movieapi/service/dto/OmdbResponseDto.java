package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(description = "save Omdb API response data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OmdbResponseDto {

    @JsonProperty("Title")
    @NotNull
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
    public String type; //movie, series, episode

    @JsonProperty("DVD")
    public String dVD;

    @JsonProperty("totalSeasons")
    public Integer totalSeasons;

    @JsonProperty("BoxOffice")
    public String boxOffice;

    @JsonProperty("Production")
    public String production;

    @JsonProperty("Website")
    public String website;


    @JsonProperty("won_best_picture")
    private Boolean wonBestPicture;

    /**
     * "Response":"False"
     */
    @JsonProperty("Response")
    public String response;

    /**
     * "Error":"Something went wrong."
     * {"Response":"False","Error":"Something went wrong."}
     */
    @JsonProperty("Error")
    private String error;

    public void addRate(RateDto rate) {
        if (ratings != null)
            ratings.add(rate);
    }

}

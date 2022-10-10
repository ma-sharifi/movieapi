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
//    @JsonDeserialize(using = BoxOfficeSerializer.class)
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
/*  MOVIE
    {
            "Title": "Black Swan",
            "Year": "2010",
            "Rated": "R",
            "Released": "17 Dec 2010",
            "Runtime": "108 min",
            "Genre": "Drama, Thriller",
            "Director": "Darren Aronofsky",
            "Writer": "Mark Heyman, Andres Heinz, John J. McLaughlin",
            "Actors": "Natalie Portman, Mila Kunis, Vincent Cassel",
            "Plot": "A committed dancer struggles to maintain her sanity after winning the lead role in a production of Tchaikovsky's \"Swan Lake\".",
            "Language": "English, French, Italian",
            "Country": "United States",
            "Awards": "Won 1 Oscar. 97 wins & 280 nominations total",
            "Poster": "https://m.media-amazon.com/images/M/MV5BNzY2NzI4OTE5MF5BMl5BanBnXkFtZTcwMjMyNDY4Mw@@._V1_SX300.jpg",
            "Ratings": [
        {
            "Source": "Internet Movie Database",
                "Value": "8.0/10"
        },
        {
            "Source": "Rotten Tomatoes",
                "Value": "85%"
        },
        {
            "Source": "Metacritic",
                "Value": "79/100"
        }
  ],
            "Metascore": "79",
            "imdbRating": "8.0",
            "imdbVotes": "759,022",
            "imdbID": "tt0947798",
            "Type": "movie",
            "DVD": "29 Mar 2011",
            "BoxOffice": "$106,954,678",
            "Production": "N/A",
            "Website": "N/A",
            "Response": "True"
    }

    SERIES
    {
      "Title": "24",
      "Year": "2001–2010",
      "Rated": "TV-14",
      "Released": "06 Nov 2001",
      "Runtime": "44 min",
      "Genre": "Action, Crime, Drama",
      "Director": "N/A",
      "Writer": "Robert Cochran, Joel Surnow",
      "Actors": "Kiefer Sutherland, Mary Lynn Rajskub, Carlos Bernard",
      "Plot": "Counter Terrorism Agent Jack Bauer races against the clock to subvert terrorist plots and save his nation from ultimate disaster.",
      "Language": "English, Russian, German, Korean, Arabic, Spanish, Serbian, Mandarin",
      "Country": "United States",
      "Awards": "Won 20 Primetime Emmys. 74 wins & 207 nominations total",
      "Poster": "https://m.media-amazon.com/images/M/MV5BMTg5OTkyNzA0NF5BMl5BanBnXkFtZTcwMDYyMDUwMg@@._V1_SX300.jpg",
      "Ratings": [
        {
          "Source": "Internet Movie Database",
          "Value": "8.4/10"
        }
      ],
      "Metascore": "N/A",
      "imdbRating": "8.4",
      "imdbVotes": "185,985",
      "imdbID": "tt0285331",
      "Type": "series",
      "totalSeasons": "9",
      "Response": "True"
    }
 */
}

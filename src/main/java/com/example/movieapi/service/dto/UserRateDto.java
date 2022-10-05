package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 *
 * https://www.omdbapi.com/?t=swan&apikey=c732beed
 *     {
 *       "Source": "Internet Movie Database",
 *       "Value": "8.0/10"
 *     },
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRateDto {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}

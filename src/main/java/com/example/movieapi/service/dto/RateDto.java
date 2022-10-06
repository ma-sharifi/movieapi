package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class RateDto {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}

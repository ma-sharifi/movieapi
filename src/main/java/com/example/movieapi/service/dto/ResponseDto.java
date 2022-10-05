package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    public ResponseDto(List<T> payload) {
        this.payload = payload;
    }

    @JsonProperty("status")
    private int status;
    private String message;
    private  String path;//transient

    private String parameters;

    @JsonIgnore
    private transient HttpStatus httpStatus;

    private List<T> payload=new ArrayList<>();
}
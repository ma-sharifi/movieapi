package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    public ResponseDto() {
        message="Success :-)";
    }

    public ResponseDto(List<T> payload) {
        this.payload = payload;
        message="Success :-)";
    }

    public ResponseDto(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @JsonProperty("error_code")
    private int errorCode;
    private String message;

    @JsonIgnore
    private HttpStatus httpStatus;

    private List<T> payload=new ArrayList<>();


}

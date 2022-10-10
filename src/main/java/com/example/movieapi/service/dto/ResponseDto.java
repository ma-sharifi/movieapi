package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data
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

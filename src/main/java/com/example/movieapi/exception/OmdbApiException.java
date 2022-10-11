package com.example.movieapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 * When Movie not found throw this exception
 */
@Getter
public class OmdbApiException extends AbstractThrowable {

    public OmdbApiException(String cause) {
        super("There is an error in calling Omdb API! Cause: "+cause, HttpStatus.INTERNAL_SERVER_ERROR,5001);
    }

}

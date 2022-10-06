package com.example.movieapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 * When Movie not found throw this exception
 */
@Getter
public class MovieNotFoundException extends AbstractThrowable {

    public MovieNotFoundException(String title) {
        super("Could not find movie with title: " + title, HttpStatus.NOT_FOUND,4041);
    }

}

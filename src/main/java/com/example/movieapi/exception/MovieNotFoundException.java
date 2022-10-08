package com.example.movieapi.exception;

import com.example.movieapi.entity.UserMovieId;
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
    public MovieNotFoundException(UserMovieId id) {
        super("Could not find movie with id: " + id, HttpStatus.NOT_FOUND,4042);
    }


}

package com.example.movieapi.exception;

import com.example.movieapi.entity.UserMovieId;
import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */
public class MovieWasNotWonException extends  AbstractThrowable {
    public MovieWasNotWonException(String title) {
        super("Movie was not won an Oscar of Best Picture with title: " + title, HttpStatus.NOT_FOUND,4043);
    }
}

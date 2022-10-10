package com.example.movieapi.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */
public class MovieWasNotWonException extends  AbstractThrowable {
    public MovieWasNotWonException(String title) {
        super("Based on csv file movie was not won an Oscar of Best Picture with title: " + title, HttpStatus.NOT_FOUND,4043);
    }
}

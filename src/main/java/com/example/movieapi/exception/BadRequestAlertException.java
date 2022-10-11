package com.example.movieapi.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

public class BadRequestAlertException extends AbstractThrowable{

    public BadRequestAlertException(String reason) {
        super("Bad request! Something wrong in client request! Reason: "+reason, HttpStatus.BAD_REQUEST, 4001);
    }
}

package com.example.movieapi.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

public class BadRequestAlertException extends AbstractThrowable{

    public BadRequestAlertException(String title,int errorCode) {
        super(title, HttpStatus.BAD_REQUEST,errorCode);
    }
    public BadRequestAlertException() {
        super("Bad request! Something wrong in client request.", HttpStatus.BAD_REQUEST, 4001);
    }
}

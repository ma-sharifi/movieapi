package com.example.movieapi.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */
public class AuthenticationException extends AbstractThrowable {

    public AuthenticationException() {
            super("JWT token not found!", HttpStatus.UNAUTHORIZED,4010);
        }
}

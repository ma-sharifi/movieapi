package com.example.movieapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 * When Movie not found throw this exception
 */
@Getter
public class CsvFileException extends AbstractThrowable {

    public CsvFileException() {
        super("There is an error in Csv File!", HttpStatus.INTERNAL_SERVER_ERROR,5002);
    }

}

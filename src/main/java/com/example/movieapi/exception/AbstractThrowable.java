package com.example.movieapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class AbstractThrowable extends RuntimeException {

    private final String message;//"Could not find account with id: "
    private final HttpStatus httpStatus; // NOT_FOUND
    private final int errorCode;
}

package com.example.movieapi.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor
@Builder
public class AbstractThrowable extends RuntimeException{

    private final String title;//"Could not find account with id: "
    private final HttpStatus httpStatus; // NOT_FOUND
    private final int errorCode;
//    private final Map<String, Object> parameters;

    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}

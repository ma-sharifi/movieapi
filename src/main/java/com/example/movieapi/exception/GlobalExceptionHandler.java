package com.example.movieapi.exception;

import com.example.movieapi.service.dto.ResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@ControllerAdvice
@NoArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MovieNotFoundException.class})
    public ResponseEntity<ResponseDto> handleMovieNotFoundException(MovieNotFoundException ex) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setHttpStatus(ex.getHttpStatus());

        if (ex.getMessage() == null || ex.getMessage().isEmpty())
            responseDto.setMessage("Movie not found.");
        else
            responseDto.setMessage(ex.getMessage());
        responseDto.setErrorCode(ex.getErrorCode());
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @ExceptionHandler(value = {BadRequestAlertException.class})
    public ResponseEntity<ResponseDto> handleBadRequestAlertException(BadRequestAlertException ex) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setHttpStatus(ex.getHttpStatus());

        if (ex.getMessage() == null || ex.getMessage() == "")
            responseDto.setMessage("Bad request! Something wrong in client request.");
        else
            responseDto.setMessage(ex.getMessage());
        responseDto.setErrorCode(ex.getErrorCode());
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

}

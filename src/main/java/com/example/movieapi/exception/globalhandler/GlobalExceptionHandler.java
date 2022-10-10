package com.example.movieapi.exception.globalhandler;

import com.example.movieapi.exception.*;
import com.example.movieapi.service.dto.ResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */

@ControllerAdvice
@NoArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ResponseDto<Void>> handleAuthenticationException(AuthenticationException ex) {
        log.debug("#handleAuthenticationException: " + ex.getMessage());
        ResponseDto<Void> responseDto = new ResponseDto<>();
        responseDto.setHttpStatus(ex.getHttpStatus());

        responseDto.setErrorCode(ex.getErrorCode());
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @ExceptionHandler(value = {CsvFileException.class, OmdbApiException.class,
            BadRequestAlertException.class, MovieNotFoundException.class})
    public ResponseEntity<ResponseDto<Void>> handleException(AbstractThrowable ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(toDto(ex));
    }

    private ResponseDto<Void> toDto(AbstractThrowable exception) {
        ResponseDto<Void> dto = new ResponseDto<>();
        dto.setHttpStatus(exception.getHttpStatus());
        dto.setMessage(exception.getMessage());
        dto.setErrorCode(exception.getErrorCode());
        return dto;
    }

}

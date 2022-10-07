package com.example.movieapi.exception;

import com.example.movieapi.service.dto.ResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

//    @ExceptionHandler(value = {MovieNotFoundException.class})
//    public ResponseEntity<ResponseDto> handleMovieNotFoundException(MovieNotFoundException ex) {
//        ResponseDto responseDto = new ResponseDto();
//        responseDto.setHttpStatus(ex.getHttpStatus());
//
//        if (ex.getMessage() == null || ex.getMessage().isEmpty())
//            responseDto.setMessage("Movie not found.");
//        else
//            responseDto.setMessage(ex.getMessage());
//        responseDto.setErrorCode(ex.getErrorCode());
//        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
//    }

//    @ExceptionHandler(value = {BadRequestAlertException.class})
//    public ResponseEntity<ResponseDto> handleBadRequestAlertException(BadRequestAlertException ex) {
//        ResponseDto responseDto = new ResponseDto();
//        responseDto.setHttpStatus(ex.getHttpStatus());
//
//        if (ex.getMessage() == null || ex.getMessage() == "")
//            responseDto.setMessage("Bad request! Something wrong in client request.");
//        else
//            responseDto.setMessage(ex.getMessage());
//        responseDto.setErrorCode(ex.getErrorCode());
//        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
//    }

    @ExceptionHandler(value = {CsvFileException.class,OmdbApiException.class,BadRequestAlertException.class,MovieNotFoundException.class})
    public ResponseEntity<ResponseDto> handleException(AbstractThrowable ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(toDto(ex));
    }

    private ResponseDto toDto(AbstractThrowable exception){
        ResponseDto dto=new ResponseDto<>();
        dto.setHttpStatus(exception.getHttpStatus());
        dto.setMessage(exception.getMessage());
        dto.setErrorCode(exception.getErrorCode());
        return dto;
     }

}

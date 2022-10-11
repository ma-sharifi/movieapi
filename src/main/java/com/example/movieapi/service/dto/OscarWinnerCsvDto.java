package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OscarWinnerCsvDto {

    private LocalDate year;
    private String category;
    private String nominee;
    private String additionalInfo;
    private Boolean won;
}

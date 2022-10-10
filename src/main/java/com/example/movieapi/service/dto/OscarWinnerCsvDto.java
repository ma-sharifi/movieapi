package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OscarWinnerCsvDto {

    private LocalDate year;
    private String category;
    private String nominee;
    private String additionalInfo;
    private Boolean won;
}

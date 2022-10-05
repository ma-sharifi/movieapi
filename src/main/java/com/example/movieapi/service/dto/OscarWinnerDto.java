package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OscarWinnerDto implements Serializable {
        private static final long serialVersionUID = 12345673223L;

        private LocalDate year;
        private String category;
        private String nominee;
        private String additionalInfo;
        private Boolean won;
}

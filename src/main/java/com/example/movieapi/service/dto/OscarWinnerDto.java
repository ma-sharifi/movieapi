package com.example.movieapi.service.dto;

import com.example.movieapi.util.serializer.GSONModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@EqualsAndHashCode(callSuper = true)
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OscarWinnerDto extends GSONModel {

        private LocalDate year;
        private String category;
        private String nominee;
        private String additionalInfo;
        private Boolean won;
}

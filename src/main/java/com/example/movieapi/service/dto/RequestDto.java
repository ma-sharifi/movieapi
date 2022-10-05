package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RequestDto {

    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "value is required")
    @Range(min = 1, max = 10, message = "value should be between 1 and 10")
    private Integer rate;
}

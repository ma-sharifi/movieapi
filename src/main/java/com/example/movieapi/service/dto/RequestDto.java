package com.example.movieapi.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Schema(description = "Save request data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDto {

    @NotNull(message = "#rate is required")
    @Range(min = 1, max = 10, message = "rate should be between 1 and 10")
    private Integer rate;

    private String title;
}

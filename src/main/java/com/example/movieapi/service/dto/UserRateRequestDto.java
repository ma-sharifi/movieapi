package com.example.movieapi.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Data
public class UserRateRequestDto implements Serializable {
    private static final long serialVersionUID = 12345673232L;

    @NotNull(message = "#title is required")
    private String title;

    @NotNull(message = "#rate is required")
    @Range(min = 1, max = 10, message = "rate must be between 1 and 10")
    private int rate;
}

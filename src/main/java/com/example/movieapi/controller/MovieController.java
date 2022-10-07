package com.example.movieapi.controller;

import com.example.movieapi.service.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */
public interface MovieController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a movie by title if it won oscar Best Picture",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie this this title not found", content = @Content)})
    @Operation(summary = "find and return a movie by title if it won Best Picture oscar")
    ResponseEntity<ResponseDto> getMovieWonOscar(
                @Parameter(description = "Movie title for checking if it won Best Picture oscar")
            @Valid @RequestParam("title") @NotNull(message = "#title is required") String title);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get a movie by imdbID",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
        @ApiResponse(responseCode = "404", description = "Movie with this imdbId not found", content = @Content)})
    @Operation(summary = "find and return a movie by imdbID")
    ResponseEntity<ResponseDto> getMovieByFromOmdbApi(
            @Parameter(description = "ImdbId for finding movie base on that")
            @Valid @PathVariable("imdb-id") String imdbId);
}

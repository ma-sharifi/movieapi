package com.example.movieapi.controller;

import com.example.movieapi.service.dto.OmdbResponseDto;
import com.example.movieapi.service.dto.RequestDto;
import com.example.movieapi.service.dto.ResponseDto;
import com.example.movieapi.service.dto.UserRateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OmdbResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie this this title not found", content = @Content)})
    @Operation(summary = "find and return a movie by title if it won Best Picture oscar")
    ResponseEntity<ResponseDto<OmdbResponseDto>> getMovieWonOscar(
            @Parameter(description = "Movie title for checking if it won Best Picture oscar", example = "The Hurt Locker")
            @Valid @RequestParam("title") @NotNull(message = "#title is required") String title);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get user rate entity saved into database and link of movie",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie this this title not found", content = @Content)})
    @Operation(summary = "Get Rate and name of move from user, then find the movie by title on Omdb API then save movie info alongside the rate")
    ResponseEntity<ResponseDto<UserRateDto>> rateByTitle(
            @Parameter(description = "rate is the rat that user give to this movie , title is the title of movie", example = "The Hurt Locker")
            @Valid @RequestBody RequestDto requestDto);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a movie by imdbID",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie with this imdbId not found", content = @Content)})
    @Operation(summary = "find and return a movie by imdbID")
    ResponseEntity<ResponseDto<OmdbResponseDto>> getMovieFromOmdbApi(
            @Parameter(description = "ImdbId for finding movie base on that", example = "tt0887912")
            @Valid @PathVariable("imdb-id") String imdbId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get top ten rated movies ordered by boxoffice",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @Operation(summary = "return top ten rated movies ordered by boxoffice")
    ResponseEntity<ResponseDto<UserRateDto>> getTopTenMove();
}
package com.example.movieapi.service;

import com.example.movieapi.service.dto.OscarWinnerCsvDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/11/22
 */
public interface OscarWinnerCsvService {
    /**
     * Load the file and save it to an ArrayList
     */
    int loadOscarWinners() throws IOException;

    OscarWinnerCsvDto findMovieByTitleAndCategory(String title);

    List<OscarWinnerCsvDto> findByTitle(String title);

    int getSize();
}

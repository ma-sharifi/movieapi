package com.example.movieapi.dto;

import com.example.movieapi.service.dto.OscarWinnerCsvDto;
import org.junit.jupiter.api.Test;

import static com.example.movieapi.service.mapper.GeneralMapper.toYear;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/11/22
 */

class OscarWinnerCsvDtoTest {

    @Test
    void shouldReturnOscarWinnerCsvDto(){
        OscarWinnerCsvDto oscarWinnerCsvDto= OscarWinnerCsvDto.builder()
                .nominee("Black Swan")
                .category("Best Picture")
                .won(false)
                .additionalInfo("Won 1 Oscar. 97 wins & 280 nominations total")
                .build();
        assertEquals("Black Swan",oscarWinnerCsvDto.getNominee());
        assertEquals("Best Picture",oscarWinnerCsvDto.getCategory());
        assertFalse(oscarWinnerCsvDto.getWon());
        assertEquals("Won 1 Oscar. 97 wins & 280 nominations total",oscarWinnerCsvDto.getAdditionalInfo());
    }
}

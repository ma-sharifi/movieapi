package com.example.movieapi.service.mapper;

import com.example.movieapi.service.dto.UserRateDto;
import org.apache.commons.csv.CSVRecord;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * @author Mahdi Sharifi
 * @since 10/10/22
 */
public enum GeneralMapper {
    INSTANCE;
    public  static UserRateDto toUserRateDto(Object[] userRateRetreive) {
        return UserRateDto.builder()
                .imdbId((String) userRateRetreive[0])
                .title((String) userRateRetreive[1])
                .boxOffice((long) userRateRetreive[2])
                .rateAverage((double) userRateRetreive[3])
                .build();
    }
    public static LocalDate toYear(CSVRecord csvRecord) {
        String date = csvRecord.get("Year");
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return LocalDate.parse(date.substring(0, 4), format);
    }
}

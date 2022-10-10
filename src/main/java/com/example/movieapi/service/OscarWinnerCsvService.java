package com.example.movieapi.service;

import com.example.movieapi.exception.MovieNotFoundException;
import com.example.movieapi.service.dto.OscarWinnerCsvDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */

@Service
@Slf4j
public class OscarWinnerCsvService {

    private static final String CATEGORY="Best Picture";

    private final File oscarWinnerFile;

    private final List<OscarWinnerCsvDto> oscarWinners = new ArrayList<>();

    public OscarWinnerCsvService(File oscarWinnerFile) {
            this.oscarWinnerFile=oscarWinnerFile;
    }

    public void loadOscarWinners() throws IOException {
            Reader in = new FileReader(oscarWinnerFile);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().parse(in);
        for (CSVRecord csvRecord : records) {
            oscarWinners.add(toOscarWinnerDto(csvRecord));
        }
    }

    public OscarWinnerCsvDto findMovieByTitleAndCategory(String title) {
        Optional<OscarWinnerCsvDto> oscarWinnerDtoOptional=oscarWinners.stream()
                .filter(movie -> (movie.getNominee().contains(title) && movie.getCategory().contains(CATEGORY))).findFirst();
        if(oscarWinnerDtoOptional.isPresent())
            log.debug("###title: "+title+" ;WON; "+oscarWinnerDtoOptional.get());
        else  log.debug("###title: "+title+" ;LOOS");
        return oscarWinnerDtoOptional.orElseThrow(() -> new MovieNotFoundException(title+" ;Based on CSV file"));
    }

    public List<OscarWinnerCsvDto> findByTitle(String title){
        return oscarWinners.stream().filter(movie ->  (movie.getNominee().contains(title))).collect(Collectors.toList());
    }

    public int getSize() {
        return oscarWinners.size();
    }

    private OscarWinnerCsvDto toOscarWinnerDto(CSVRecord csvRecord) {
        OscarWinnerCsvDto dto=new OscarWinnerCsvDto();
        dto.setYear(toYear(csvRecord));
        dto.setCategory(csvRecord.get("Category"));
        dto.setNominee(csvRecord.get("Nominee"));
        dto.setAdditionalInfo(csvRecord.get("Additional Info"));
        dto.setWon(csvRecord.get("Won").equals("YES"));
        return dto;
    }

    private LocalDate toYear(CSVRecord csvRecord) {
        String date = csvRecord.get("Year");
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return LocalDate.parse(date.substring(0, 4), format);
    }

}

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

import static com.example.movieapi.service.mapper.GeneralMapper.toYear;

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

    public int loadOscarWinners() throws IOException {
            Reader in = new FileReader(oscarWinnerFile);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().parse(in);
        int counter=0;
        for (CSVRecord csvRecord : records) {
            oscarWinners.add(toOscarWinnerDto(csvRecord));
            counter++;
        }
        return counter;
    }

    public OscarWinnerCsvDto findMovieByTitleAndCategory(String title) {
        Optional<OscarWinnerCsvDto> oscarWinnerDtoOptional=oscarWinners.stream()
                .filter(movie -> (movie.getNominee().contains(title) && movie.getCategory().contains(CATEGORY))).findFirst();
        if(oscarWinnerDtoOptional.isPresent())
            log.debug("#title: "+title+" ;WON; "+oscarWinnerDtoOptional.get());
        else  log.debug("#title: "+title+" ;LOOS");
        return oscarWinnerDtoOptional.orElseThrow(() -> new MovieNotFoundException(title+" ;Based on CSV file"));
    }

    public List<OscarWinnerCsvDto> findByTitle(String title){
        return oscarWinners.stream().filter(movie ->  (movie.getNominee().contains(title))).collect(Collectors.toList());
    }

    public int getSize() {
        return oscarWinners.size();
    }

    private OscarWinnerCsvDto toOscarWinnerDto(CSVRecord csvRecord) {
        return OscarWinnerCsvDto.builder()
                .year(toYear(csvRecord))
                .category(csvRecord.get("Category"))
                .nominee(csvRecord.get("Nominee"))
                .additionalInfo(csvRecord.get("Additional Info"))
                .won(csvRecord.get("Won").equals("YES"))
                .build();
    }



}

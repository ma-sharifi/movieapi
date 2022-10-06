package com.example.movieapi.service;

import com.example.movieapi.service.dto.OscarWinnerDto;
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

    private final List<OscarWinnerDto> oscarWinners = new ArrayList<>();

    public OscarWinnerCsvService(File oscarWinnerFile) {
            this.oscarWinnerFile=oscarWinnerFile;
    }

    public void loadOscarWinners() throws IOException {
        Reader in = new FileReader(oscarWinnerFile);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().parse(in);
        for (CSVRecord record : records) {
            oscarWinners.add(toOscarWinnerDto(record));
        }
    }

    public Boolean isWonByTitleForBestPicture(String title) {
        log.info("#Call isWonByTitleForBestPicture: "+title);
        log.info("#Call isWonByTitleForBestPicture: "+oscarWinners.stream().filter(movie ->  (movie.getNominee().contains(title))).collect(Collectors.toList()));
        Optional<OscarWinnerDto> oscarWinnerDtoOptional=oscarWinners.stream()
                .filter(movie -> movie.getWon() && (movie.getNominee().contains(title) && movie.getCategory().contains(CATEGORY))).findFirst();
        if(oscarWinnerDtoOptional.isPresent())
            log.info("###title: "+title+" ;WON; "+oscarWinnerDtoOptional.get());
        else  log.error("###title: "+title+" ;LOOS");

        return oscarWinners.stream()
                .anyMatch(movie -> movie.getWon() && (movie.getNominee().contains(title) && movie.getCategory().contains(CATEGORY)));
    }

    public List<OscarWinnerDto> findByTitle(String title){
        return oscarWinners.stream().filter(movie ->  (movie.getNominee().contains(title))).collect(Collectors.toList());
    }

    public int getSize() {
        return oscarWinners.size();
    }

    private OscarWinnerDto toOscarWinnerDto(CSVRecord csvRecord) {
        OscarWinnerDto dto=new OscarWinnerDto();
        dto.setYear(toYear(csvRecord));
        dto.setCategory(csvRecord.get("Category"));
        dto.setNominee(csvRecord.get("Nominee"));
        dto.setAdditionalInfo(csvRecord.get("Additional Info"));
        dto.setWon(csvRecord.get("Won").equals("YES"));
        return dto;
    }

    private LocalDate toYear(CSVRecord record) {
        String date = record.get("Year");
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return LocalDate.parse(date.substring(0, 4), format);
    }

}

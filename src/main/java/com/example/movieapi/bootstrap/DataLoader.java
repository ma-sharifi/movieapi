package com.example.movieapi.bootstrap;

import com.example.movieapi.service.OscarWinnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

@Configuration
//@Profile("!prod")
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final Environment environment;

    private final OscarWinnerService oscarWinnerService;

    public DataLoader(OscarWinnerService oscarWinnerService, Environment environment) {
        this.oscarWinnerService = oscarWinnerService;
        this.environment = environment;
    }

    @Override
    public void run(String... args){

        log.info("#data is loading.....");
        loadData();
        log.info("#data is loaded.");
        log.info("#Currently active profile - " + Arrays.toString(environment.getActiveProfiles()));

    }

    public void loadData() {
        try {
            oscarWinnerService.loadOscarWinners();
        } catch (IOException e) {
            log.error("Exception while loading data from CSV file");
        }
    }
}

package com.example.movieapi.bootstrap;

import com.example.movieapi.entity.UserRate;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.OscarWinnerService;
import com.example.movieapi.service.dto.UserRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserRateRepository userRateRepository;

    public DataLoader(Environment environment, OscarWinnerService oscarWinnerService, UserRateRepository userRateRepository) {
        this.environment = environment;
        this.oscarWinnerService = oscarWinnerService;
        this.userRateRepository = userRateRepository;
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

        try{
            UserRateDto userRateDto = new UserRateDto();
            userRateDto.setSource("Mahdi");
            customer.setSurname("Sharifi");

            customer.addAccount(account);
            customer.addAccount(account2);
            customerRepository.save(customer);
            log.debug("#customer1 : {}", customer);
        }catch (Exception e){

        }
    }
}

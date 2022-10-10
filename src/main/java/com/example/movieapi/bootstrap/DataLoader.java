package com.example.movieapi.bootstrap;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.repository.UserRateRepository;
import com.example.movieapi.service.OscarWinnerCsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("!prod")
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final Environment environment;

    private final OscarWinnerCsvService oscarWinnerService;

    private final UserRateRepository userRateRepository;

    private final List<UserRate> list=List.of(new UserRate(
            new UserMovieId("tt0947798","mahdi"),0,"Black Swan",0L)
            ,new UserRate(new UserMovieId("tt0299658","mahdi"),1,"Chicago",1L)
            ,new UserRate(new UserMovieId("tt1375666","mahdi"),2,"Inception",2L)
            ,new UserRate(new UserMovieId("tt1504320","mahdi"),3,"The King's Speech",3L)
            ,new UserRate(new UserMovieId("tt1542344","mahdi"),4,"127 Hours",4L)
            ,new UserRate(new UserMovieId("tt0268978","mahdi"),8,"A Beautiful Mind",5L)
            ,new UserRate(new UserMovieId("tt0268978","ali"),10,"A Beautiful Mind",5L)
            ,new UserRate(new UserMovieId("tt0119217","mahdi"),7,"Good Will Hunting",6L)
            ,new UserRate(new UserMovieId("tt1014759","mahdi"),7,"Alice in Wonderland",7L)
            ,new UserRate(new UserMovieId("tt0371746","mahdi"),9,"Iron Man",8L)
            ,new UserRate(new UserMovieId("tt0887912","mahdi"),9,"The Hurt Locker",9L)
            ,new UserRate(new UserMovieId("tt0405159","mahdi"),10,"Million Dollar Baby",10L)
            ,new UserRate(new UserMovieId("tt0405159","mahdi"),10,"Million Dollar Baby",10L)
            ,new UserRate(new UserMovieId("tt0077869","mahdi"),10,"The Lord of the Rings",11L)
            ,new UserRate(new UserMovieId("tt0120689","mahdi"),10,"The Green Mile",12L)
            ,new UserRate(new UserMovieId("tt0120689","ali"),10,"The Green Mile",12L)
            ,new UserRate(new UserMovieId("tt0120689","Hasan"),10,"The Green Mile",12L)
    );

    public DataLoader(Environment environment, OscarWinnerCsvService oscarWinnerService, UserRateRepository userRateRepository) {
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
        } catch (Exception e) {
            log.error("Exception while loading data from CSV file");
        }


//        List<UserRate> list=List.of(new UserRate(new UserMovieId("tt0947798","mahdi"),8,"Black Swan",106954678L)
//        ,new UserRate(new UserMovieId("tt1375666","mahdi"),9,"Inception",92587330L)
//        ,new UserRate(new UserMovieId("tt1504320","mahdi"),7,"The King's Speech",138797449L)
//        ,new UserRate(new UserMovieId("tt1542344","mahdi"),6,"127 Hours",8335230L)
//        ,new UserRate(new UserMovieId("tt1014759","mahdi"),7,"Alice in Wonderland",334191110L)
//        ,new UserRate(new UserMovieId("tt0371746","mahdi"),7,"Iron Man",319034126L)
//        ,new UserRate(new UserMovieId("tt0887912","mahdi"),7,"The Hurt Locker",17017811L)
//        ,new UserRate(new UserMovieId("tt0405159","mahdi"),10,"Million Dollar Baby",100492203L)
//        ,new UserRate(new UserMovieId("tt0077869","mahdi"),10,"The Lord of the Rings",30471420L)
//        ,new UserRate(new UserMovieId("tt0120689","mahdi"),10,"The Green Mile",136801374L)
//        ,new UserRate(new UserMovieId("tt0299658","mahdi"),6,"Chicago",170687518L)
//        ,new UserRate(new UserMovieId("tt0268978","mahdi"),9,"A Beautiful Mind",170742341L)
//        ,new UserRate(new UserMovieId("tt0119217","mahdi"),9,"Good Will Hunting",138433435L)
//                );




        userRateRepository.saveAll(list);
        log.info("12 UserRate data saved on DB.");
    }
}

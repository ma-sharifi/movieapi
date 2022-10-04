package com.example.movieapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
@Configuration
public class CsvFileConfig {

    @Value("classpath:academy_awards.csv")
    private Resource resource;

    @Bean(name = "oscarWinnerFile")
    public File getOscarWinnerFile() throws IOException {
        return resource.getFile();
    }

}

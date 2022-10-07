package com.example.movieapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Mahdi Sharifi
 * @since 10/6/22
 */
@Configuration
@EnableAsync//annotation switches on Spring’s ability to run @Async methods in a background thread pool.
public class AsyncConfiguration  {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("third-party-call-pool-");
        executor.initialize();
        return executor;
    }

}
package com.carter.phargate.config;

import com.carter.phargate.ingestor.BootsFileIngestionService;
import com.carter.phargate.ingestor.BootsIngestionService;
import com.carter.phargate.ingestor.IngestionEngine;
import com.carter.phargate.util.RestClient;
import com.carter.phargate.util.RestClientFactory;
import com.google.common.collect.ImmutableSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public RestClient rateLimitedRestClient() {
        return RestClientFactory.newRateLimitedClient(5, Duration.ofSeconds(60), true);
    }

    @Bean
    public IngestionEngine ingestionEngine(BootsIngestionService bootsIngestionService) {
        return new IngestionEngine(
                ImmutableSet.of(bootsIngestionService)
        );
    }

}

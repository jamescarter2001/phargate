package com.carter.phargate.config;

import com.carter.phargate.data.source.PharmacyChainDataSource;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.util.http.RestClient;
import com.carter.phargate.util.http.RestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestClient rateLimitedRestClient() {
        return RestClientFactory.newRateLimitedClient(5, Duration.ofMinutes(1), true);
    }

    @Bean
    public BootsPharmacyClient bootsPharmacyClient(RestClient rateLimitedRestClient, PharmacyChainDataSource pharmacyChainDataSource) {
        return new BootsPharmacyClient(rateLimitedRestClient, pharmacyChainDataSource.getPharmacyChainByPharmacyTypeMapper());
    }

}

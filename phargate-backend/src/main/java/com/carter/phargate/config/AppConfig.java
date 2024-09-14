package com.carter.phargate.config;

import com.carter.phargate.data.source.PharmacyChainDataSource;
import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.ingestor.PharmacyIngestor;
import com.carter.phargate.pharmacy.PharmacyClient;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.pharmacy.boots.BootsPharmacyFileClient;
import com.carter.phargate.util.RestClient;
import com.carter.phargate.util.RestClientFactory;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestClient rateLimitedRestClient() {
        return RestClientFactory.newRateLimitedClient(5, Duration.ofMinutes(1), true);
    }

    @Bean("bootsPharmacyClient")
    public PharmacyClient bootsPharmacyClient(RestClient rateLimitedRestClient, PharmacyChainDataSource pharmacyChainDataSource) {
        return new BootsPharmacyClient(rateLimitedRestClient, pharmacyChainDataSource.getPharmacyChainByPharmacyTypeMapper());
    }

    @Bean("bootsPharmacyLocalClient")
    public PharmacyClient bootsPharmacyLocalClient() {
        return new BootsPharmacyFileClient();
    }

    @Bean
    public PharmacyIngestor pharmacyIngestor(@Qualifier("bootsPharmacyLocalClient") PharmacyClient bootsPharmacyLocalClient, PharmacyDataSource pharmacyDataSource) {
        return new PharmacyIngestor(
                ImmutableList.of(
                        bootsPharmacyLocalClient
                ),
                pharmacyDataSource
        );
    }

}

package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.pharmacy.PharmacyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class PharmacyIngestor implements Ingestor {

    private final List<PharmacyClient> pharmacyClients;
    private final PharmacyDataSource pharmacyDataSource;

    public PharmacyIngestor(List<PharmacyClient> pharmacyClients, PharmacyDataSource pharmacyDataSource) {
        this.pharmacyClients = pharmacyClients;
        this.pharmacyDataSource = pharmacyDataSource;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void ingest() {
        log.info("Begin Ingestion");
        pharmacyClients.forEach(c -> pharmacyDataSource.saveAll(c.getPharmacies()));
        log.info("End Ingestion");
    }
}

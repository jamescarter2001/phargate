package com.carter.phargate.ingestor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Slf4j
public class IngestionEngine implements IngestionService {

    private final Set<IngestionService> ingestionServices;

    public IngestionEngine(Set<IngestionService> ingestionServices) {
        this.ingestionServices = ingestionServices;

        ingestMedicineStock();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void ingestPharmacies() {
        log.info("Begin Pharmacy Ingestion");
        ingestionServices.forEach(IngestionService::ingestPharmacies);
        log.info("End Pharmacy Ingestion");
    }

    @Override
    @Scheduled(cron = "0 0 4 * * *")
    public void ingestMedicineStock() {
        log.info("Begin Medicine Stock Ingestion");
        ingestionServices.forEach(IngestionService::ingestMedicineStock);
        log.info("End Medicine Stock Ingestion");
    }

}

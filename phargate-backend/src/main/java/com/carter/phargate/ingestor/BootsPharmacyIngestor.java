package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BootsPharmacyIngestor implements Ingestor {

    private final BootsPharmacyClient bootsPharmacyClient;
    private final PharmacyDataSource pharmacyDataSource;

    public BootsPharmacyIngestor(BootsPharmacyClient bootsPharmacyClient, PharmacyDataSource pharmacyDataSource) {
        this.bootsPharmacyClient = bootsPharmacyClient;
        this.pharmacyDataSource = pharmacyDataSource;

        ingest();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void ingest() {
        log.info("Begin import job");
        List<Pharmacy> pharmacies = bootsPharmacyClient.getPharmacies();
        pharmacyDataSource.saveAll(pharmacies);
        log.info("End import job");
    }
}

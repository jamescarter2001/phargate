package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.MedicineDataSource;
import com.carter.phargate.data.source.MedicineStockDataSource;
import com.carter.phargate.pharmacy.PharmacyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class MedicineStockIngestor implements Ingestor {

    private final Set<PharmacyClient> pharmacyClients;
    private final MedicineDataSource dataSource;
    private final
    private final MedicineStockDataSource medicineStockDataSource;

    @Override
    @Scheduled(cron = "0 0 4 * * *")
    public void ingest() {
        log.info("Begin Pharmacy Ingestion");
        pharmacyClients.forEach(pharmacyClient -> medicineStockDataSource.saveAll(pharmacyClient.))
        log.info("End Pharmacy Ingestion");
    }

}

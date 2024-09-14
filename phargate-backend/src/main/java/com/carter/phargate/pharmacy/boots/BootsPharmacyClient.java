package com.carter.phargate.pharmacy.boots;

import com.carter.phargate.pharmacy.boots.model.BootsPharmacy;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacyItemStockRequest;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacyItemStockResult;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacySearchResult;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacyStockLevel;
import com.carter.phargate.util.CollectionsX;
import com.carter.phargate.util.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootsPharmacyClient {

    private static final int MEDICINE_BATCH_SIZE = 1;
    private static final int PHARMACY_BATCH_SIZE = 10;

    private final RestClient restClient;

    public List<BootsPharmacy> getPharmacies() {

        List<BootsPharmacy> result = new ArrayList<>();

        int offset = 0;
        int total = -1;
        while (total == -1 || offset < total) {
            BootsPharmacySearchResult response = restClient.get(
                    String.format("https://www.boots.com/online/psc/search/store?from=%d", offset),
                    BootsPharmacySearchResult.class
            );

            total = response.total();

            log.info("Loading pharmacies - Progress: ({}/{})", offset + 10, total);
            List<BootsPharmacy> pharmacies = response.pharmacies();

            if (pharmacies != null && !pharmacies.isEmpty()) {
                result.addAll(pharmacies);
                offset = offset + 10;
            }
        }
        if (total != result.size()) {
            log.warn("Result size does not match expected API size! {} != {}", result.size(), total);
        }
        log.info("Loaded {} pharmacies", result.size());
        return result;
    }

    public List<BootsPharmacyStockLevel> getStockLevelsByPharmacyIdsAndMedicineIds(List<Long> pharmacyIds, List<Long> medicineIds) {

        List<BootsPharmacyStockLevel> medicineStocks = new ArrayList<>();

        for (int i = 0; i < medicineIds.size(); i = i + MEDICINE_BATCH_SIZE) {

            List<String> mids = CollectionsX.safeSubList(medicineIds, i, i + MEDICINE_BATCH_SIZE)
                    .stream()
                    .map(String::valueOf)
                    .toList();

            for (int fromIndex = 0; fromIndex < pharmacyIds.size(); fromIndex = fromIndex + PHARMACY_BATCH_SIZE) {
                int toIndex = fromIndex + PHARMACY_BATCH_SIZE;
                AtomicLong atomicLong = new AtomicLong(0);
                List<Long> pids = CollectionsX.paddedSubList(pharmacyIds, 10, atomicLong::incrementAndGet, fromIndex, toIndex);

                log.info("Importing Boots pharmacy stock levels: {} - ({}/{})", mids.get(0), fromIndex, pharmacyIds.size());
                log.info("Importing stock levels for Boots pharmacies: {}", pids);

                try {
                    BootsPharmacyItemStockRequest request = new BootsPharmacyItemStockRequest(mids, pids);
                    BootsPharmacyItemStockResult result = restClient.post("https://www.boots.com/online/psc/itemStock", request, BootsPharmacyItemStockResult.class, Map.of("Content-Type", "application/json; charset=utf-8"));
                    Optional.ofNullable(result.stockLevels()).ifPresent(medicineStocks::addAll);
                } catch (Exception e) {
                    log.error("Failed to import stock levels for Boots pharmacies: {}", pids);
                }
            }
        }

        return medicineStocks;
    }
}

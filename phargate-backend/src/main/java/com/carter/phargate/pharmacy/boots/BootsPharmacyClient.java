package com.carter.phargate.pharmacy.boots;

import com.carter.phargate.model.Medicine;
import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.MedicineStockLevel;
import com.carter.phargate.model.PharmacyId;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacy;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacyItemStockRequest;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacyItemStockResult;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacySearchResult;
import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyType;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.pharmacy.PharmacyClient;
import com.carter.phargate.util.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class BootsPharmacyClient implements PharmacyClient {

    private static final int MEDICINE_BATCH_SIZE = 10;
    private static final int PHARMACY_BATCH_SIZE = 10;

    private final RestClient restClient;

    private final Function<PharmacyType, PharmacyChain> pharmacyChainIdByPharmacyType;
    private final Function<String, MedicineStockLevel> medicineStockLevelByBootsStockLevel;
    private final BiFunction<String, PharmacyType, Medicine> medicineBySourceIdAndPharmacyChainId;

    public List<Pharmacy> getPharmacies() {

        List<Pharmacy> result = new ArrayList<>();

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
                result.addAll(
                        pharmacies.stream()
                        .map(bp -> bp.toPharmacy(pharmacyChainIdByPharmacyType))
                        .toList()
                );
                offset = offset + 10;
            }
        }
        if (total != result.size()) {
            log.warn("Result size does not match expected API size! {} != {}", result.size(), total);
        }
        log.info("Loaded {} pharmacies", result.size());
        return result;
    }

    @Override
    public Map<MedicineId, List<MedicineStock>> getStockByPharmacyIdsAndMedicineIds(List<Long> pharmacyIds, List<Long> medicineIds) {
        List<MedicineStock> medicineStocks = new ArrayList<>();

        for (int i = 0; i < medicineIds.size(); i = i + MEDICINE_BATCH_SIZE) {

            List<String> mids = medicineIds.subList(i, MEDICINE_BATCH_SIZE)
                    .stream()
                    .map(String::valueOf)
                    .toList();

            for (int j = 0; j < pharmacyIds.size(); j = j + PHARMACY_BATCH_SIZE) {
                List<Long> pids = pharmacyIds.subList(j, PHARMACY_BATCH_SIZE);
                BootsPharmacyItemStockRequest request = new BootsPharmacyItemStockRequest(mids, pids);
                BootsPharmacyItemStockResult result = restClient.post("https://www.boots.com/online/psc/itemStock", request, BootsPharmacyItemStockResult.class);
                medicineStocks.addAll(
                        result.stockLevels().stream()
                                .map(ms -> ms.toMedicineStock(medicineBySourceIdAndPharmacyChainId, pharmacyChainIdByPharmacyType, medicineStockLevelByBootsStockLevel))
                                .toList()
                );
            }
        }

        return medicineStocks.stream()
                .collect(Collectors.groupingBy(MedicineStock::medicineId));
    }
}

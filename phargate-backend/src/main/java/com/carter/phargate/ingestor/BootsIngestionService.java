package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.MedicineDataSource;
import com.carter.phargate.data.source.MedicineStockDataSource;
import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.model.MedicineSourceId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.model.PharmacyChainId;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.pharmacy.boots.mapper.MedicineStockLevelByBootsStockLevelMapper;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacy;
import com.google.common.base.Functions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootsIngestionService implements IngestionService {

    private final BootsPharmacyClient bootsPharmacyClient;

    private final PharmacyDataSource pharmacyDataSource;
    private final MedicineDataSource medicineDataSource;
    private final MedicineStockDataSource medicineStockDataSource;

    private final MedicineStockLevelByBootsStockLevelMapper medicineStockLevelByBootsStockLevelMapper;

    @Override
    public void ingestPharmacies() {
        List<Pharmacy> pharmacies = bootsPharmacyClient.getPharmacies().stream()
                .map(BootsPharmacy::toPharmacy)
                .toList();

        pharmacyDataSource.saveAll(pharmacies);
    }

    @Override
    public void ingestMedicineStock() {
        Map<Long, MedicineSourceId> medicineSourceIdMap = medicineDataSource.getSourceIdsByPharmacyChainId(PharmacyChainId.BOOTS)
                .stream()
                .limit(1) // TODO: take this out
                .collect(Collectors.toMap(MedicineSourceId::sourceId, Functions.identity()));
        Map<Long, Pharmacy> pharmacyMap = pharmacyDataSource.getByPharmacyChainId(PharmacyChainId.BOOTS)
                .stream()
                .collect(Collectors.toMap(Pharmacy::sourceId, Functions.identity(), (a, b) -> b));

        log.info("Importing Boots stock levels - {} Medicines - {} Pharmacies", medicineSourceIdMap.size(), pharmacyMap.size());

        List<MedicineStock> medicineStock = bootsPharmacyClient.getStockLevelsByPharmacyIdsAndMedicineIds(
                        new ArrayList<>(pharmacyMap.keySet()),
                        new ArrayList<>(medicineSourceIdMap.keySet())
                )
                .stream()
                .map(bsl -> bsl.toMedicineStock(
                        id -> Optional.ofNullable(medicineSourceIdMap.get(id)).map(MedicineSourceId::medicineId).orElse(null),
                        id -> Optional.ofNullable(pharmacyMap.get(id)).map(Pharmacy::pharmacyId).orElse(null),
                        medicineStockLevelByBootsStockLevelMapper
                )).toList();

        medicineStockDataSource.saveAll(medicineStock);

        log.info("Done importing Boots stock levels");
    }
}

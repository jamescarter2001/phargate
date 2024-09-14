package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.MedicineDataSource;
import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.model.PharmacyChainId;
import com.carter.phargate.model.PharmacyId;
import com.carter.phargate.pharmacy.PharmacyClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BootsIngestionService implements Ingestor {

    private final PharmacyClient bootsPharmacyClient;
    private final PharmacyDataSource pharmacyDataSource;
    private final MedicineDataSource medicineDataSource;

    @Override
    public void ingestPharmacies() {

    }

    @Override
    public void ingestMedicineStock() {
        List<PharmacyId> pharmacyIds = pharmacyDataSource.
        List<Long> medicineSourceIds = medicineDataSource.getSourceIdsByPharmacyChainId(PharmacyChainId.BOOTS);
    }
}

package com.carter.phargate.pharmacy.boots;

import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.pharmacy.PharmacyClient;
import com.carter.phargate.util.FilesX;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * File-based client for importing Boots pharmacy data, serving as a workaround
 * for the strict rate limit of the Boots API.
 */
@Slf4j
public class BootsPharmacyFileClient implements PharmacyClient {
    @Override
    public List<Pharmacy> getPharmacies() {
        try {
            File pharmaciesFile = FilesX.fromClassPath("pharmacies.json");
            String data = new String(Files.readAllBytes(pharmaciesFile.toPath()));
            return new ObjectMapper().readValue(data, new TypeReference<>() {});
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Map<MedicineId, List<MedicineStock>> getStockByPharmacyIdsAndMedicineIds(List<Long> pharmacyIds, List<Long> medicineIds) {
        return Collections.emptyMap();
    }
}

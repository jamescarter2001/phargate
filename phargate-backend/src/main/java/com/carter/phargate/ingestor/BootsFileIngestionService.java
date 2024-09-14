package com.carter.phargate.ingestor;

import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.util.FilesX;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * File-based client for importing Boots pharmacy data, serving as a workaround
 * for the strict rate limit of the Boots API.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BootsFileIngestionService implements IngestionService {

    private final PharmacyDataSource pharmacyDataSource;

    @Override
    public void ingestPharmacies() {
        try {
            File pharmaciesFile = FilesX.fromClassPath("pharmacies.json");
            String data = new String(Files.readAllBytes(pharmaciesFile.toPath()));
            List<Pharmacy> pharmacies = new ObjectMapper().readValue(data, new TypeReference<>() {});
            pharmacyDataSource.saveAll(pharmacies);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void ingestMedicineStock() {

    }
}

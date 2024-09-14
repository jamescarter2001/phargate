package com.carter.phargate.ingestor;

@FunctionalInterface
public interface Ingestor {

    void ingestPharmacies();
    void ingestMedicineStock();

}

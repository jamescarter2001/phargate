package com.carter.phargate.pharmacy.boots.model;

import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.MedicineStockLevel;
import com.carter.phargate.model.PharmacyId;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.Function;
import java.util.function.LongFunction;

public record BootsPharmacyStockLevel(
        @JsonProperty("storeId") String storeId,
        @JsonProperty("productId") String productId,
        @JsonProperty("stockLevel") String stockLevel
) {

    public MedicineStock toMedicineStock(
            LongFunction<MedicineId> medicineIdByMedicineSourceId,
            LongFunction<PharmacyId> pharmacyIdByPharmacySourceId,
            Function<String, MedicineStockLevel> medicineStockLevelByBootsStockLevel
    ) {
        return MedicineStock.builder()
                .medicineId(medicineIdByMedicineSourceId.apply(Long.parseLong(productId)))
                .pharmacyId(pharmacyIdByPharmacySourceId.apply(Long.parseLong(storeId)))
                .level(medicineStockLevelByBootsStockLevel.apply(stockLevel))
                .build();
    }

}

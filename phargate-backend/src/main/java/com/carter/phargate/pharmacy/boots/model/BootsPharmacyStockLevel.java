package com.carter.phargate.pharmacy.boots.model;

import com.carter.phargate.model.Medicine;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.MedicineStockLevel;
import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.BiFunction;
import java.util.function.Function;

public record BootsPharmacyStockLevel(
        @JsonProperty("storeId") String storeId,
        @JsonProperty("productId") String productId,
        @JsonProperty("stockLevel") String stockLevel
) {

    public MedicineStock toMedicineStock(
            BiFunction<String, PharmacyType, Medicine> medicineBySourceIdAndPharmacyChainId,
            Function<PharmacyType, PharmacyChain> pharmacyChainByPharmacyType,
            Function<String, MedicineStockLevel> medicineStockLevelByBootsStockLevel
    ) {
        return MedicineStock.builder()
                .medicineId(medicineBySourceIdAndPharmacyChainId.apply(productId, PharmacyType.BOOTS).medicineId())
                .pharmacyChainId(pharmacyChainByPharmacyType.apply(PharmacyType.BOOTS).pharmacyChainId())
                .level(medicineStockLevelByBootsStockLevel.apply(stockLevel))
                .build();
    }

}

package com.carter.phargate.model;

import lombok.Builder;

import javax.annotation.Nullable;

@Builder
public record MedicineStock(
        MedicineId medicineId,
        PharmacyChainId pharmacyChainId,
        @Nullable Long numericalLevel,
        MedicineStockLevel level
) {}
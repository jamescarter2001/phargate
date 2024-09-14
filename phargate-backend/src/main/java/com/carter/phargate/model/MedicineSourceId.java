package com.carter.phargate.model;

import lombok.Builder;

@Builder
public record MedicineSourceId(
        MedicineId medicineId,
        PharmacyChainId pharmacyChainId,
        long sourceId
) {}

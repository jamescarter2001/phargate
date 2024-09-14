package com.carter.phargate.model;

import lombok.Builder;

@Builder
public record Medicine(
        MedicineId medicineId,
        MedicineTypeId medicineTypeId,
        String name
) {}

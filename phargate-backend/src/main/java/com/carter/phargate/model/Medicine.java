package com.carter.phargate.model;

public record Medicine(
        MedicineId medicineId,
        MedicineTypeId medicineTypeId,
        String name
) {}

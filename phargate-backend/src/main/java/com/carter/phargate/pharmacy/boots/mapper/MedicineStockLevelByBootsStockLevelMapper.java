package com.carter.phargate.pharmacy.boots.mapper;

import com.carter.phargate.model.MedicineStockLevel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicineStockLevelByBootsStockLevelMapper {

    public static MedicineStockLevel map(String value) {
        return switch (value) {
            case "R" -> MedicineStockLevel.RED;
            case "A" -> MedicineStockLevel.AMBER;
            case "G" -> MedicineStockLevel.GREEN;
            default -> MedicineStockLevel.UNKNOWN;
        };
    }

}

package com.carter.phargate.pharmacy.boots.mapper;

import com.carter.phargate.model.MedicineStockLevel;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MedicineStockLevelByBootsStockLevelMapper implements Function<String, MedicineStockLevel> {
    @Override
    public MedicineStockLevel apply(String s) {
        return switch (s) {
            case "R" -> MedicineStockLevel.RED;
            case "A" -> MedicineStockLevel.AMBER;
            case "G" -> MedicineStockLevel.GREEN;
            default -> MedicineStockLevel.UNKNOWN;
        };
    }
}

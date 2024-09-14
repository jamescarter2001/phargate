package com.carter.phargate.data.entity;

import com.carter.phargate.data.entity.id.MedicineStockEntityId;
import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.MedicineStockLevel;
import com.carter.phargate.model.PharmacyId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

@Entity
@IdClass(MedicineStockEntityId.class)
@Table(name = "pg_medicine_stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineStockEntity {

    @Id
    private Long medicineId;
    @Id
    private Long pharmacyId;
    @Enumerated(EnumType.STRING)
    private MedicineStockLevel level;
    private Integer numericalLevel;
    private Instant lastUpdatedAt;

    public static MedicineStockEntity fromMedicineStock(MedicineStock medicineStock, Instant lastUpdatedAt) {
        return MedicineStockEntity.builder()
                .medicineId(Optional.ofNullable(medicineStock.medicineId()).map(MedicineId::id).orElse(null))
                .pharmacyId(Optional.ofNullable(medicineStock.pharmacyId()).map(PharmacyId::id).orElse(null))
                .level(medicineStock.level())
                .numericalLevel(medicineStock.numericalLevel())
                .lastUpdatedAt(lastUpdatedAt)
                .build();
    }

    public MedicineStock toMedicineStock() {
        return MedicineStock.builder()
                .medicineId(new MedicineId(medicineId))
                .pharmacyId(new PharmacyId(pharmacyId))
                .level(level)
                .numericalLevel(numericalLevel)
                .build();
    }

}

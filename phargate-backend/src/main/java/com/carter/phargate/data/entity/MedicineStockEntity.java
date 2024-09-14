package com.carter.phargate.data.entity;

import com.carter.phargate.data.entity.id.MedicinePharmacyChainId;
import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.MedicineStockLevel;
import com.carter.phargate.model.PharmacyId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(MedicinePharmacyChainId.class)
@Table(name = "pg_medicine_stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineStockEntity {

    @Id
    private long medicineId;
    @Id
    private long pharmacyId;
    private MedicineStockLevel level;
    private Integer numericalLevel;

    public static MedicineStockEntity fromMedicineStock(MedicineStock medicineStock) {
        return MedicineStockEntity.builder()
                .medicineId(medicineStock.medicineId().id())
                .pharmacyId(medicineStock.pharmacyId().id())
                .level(medicineStock.level())
                .numericalLevel(medicineStock.numericalLevel())
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

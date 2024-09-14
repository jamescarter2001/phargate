package com.carter.phargate.data.entity;

import com.carter.phargate.data.entity.id.MedicinePharmacyChainId;
import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineSourceId;
import com.carter.phargate.model.PharmacyChainId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pg_medicine_source_ids")
@IdClass(MedicinePharmacyChainId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineSourceIdEntity {

    @Id
    private long medicineId;
    @Id
    private PharmacyChainId pharmacyChainId;
    private long sourceId;

    public MedicineSourceId toMedicineSourceId() {
        return MedicineSourceId.builder()
                .medicineId(new MedicineId(medicineId))
                .pharmacyChainId(pharmacyChainId)
                .sourceId(sourceId)
                .build();
    }

}

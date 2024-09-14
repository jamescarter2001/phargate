package com.carter.phargate.data.entity;

import com.carter.phargate.model.Medicine;
import com.carter.phargate.model.MedicineId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pg_medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineEntity {

    @Id
    private Long medicineId;
    private String name;

    public Medicine toMedicine() {
        return Medicine.builder()
                .medicineId(new MedicineId(medicineId))
                .name(name)
                .build();
    }
}

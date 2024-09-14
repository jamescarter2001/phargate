package com.carter.phargate.data.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MedicineStockEntityId {

    private long medicineId;
    private long pharmacyId;

}

package com.carter.phargate.data.entity.id;

import com.carter.phargate.model.PharmacyChainId;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MedicinePharmacyChainId {

    private long medicineId;
    private PharmacyChainId pharmacyChainId;

}

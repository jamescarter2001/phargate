package com.carter.phargate.data.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PharmacyPharmacyChainId {

    private long pharmacyId;
    private long pharmacyChainId;

}

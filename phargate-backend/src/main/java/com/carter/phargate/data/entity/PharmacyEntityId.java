package com.carter.phargate.data.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PharmacyEntityId {

    private long pharmacyId;
    private long pharmacyChainId;

}

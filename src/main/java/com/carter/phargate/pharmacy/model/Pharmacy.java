package com.carter.phargate.pharmacy.model;

import lombok.Builder;
import org.jmolecules.ddd.types.AggregateRoot;

@Builder
public record Pharmacy(
        PharmacyId pharmacyId,
        PharmacyChainId pharmacyChainId,
        long sourceId,
        String addressLine1,
        String town,
        String county,
        String postcode,
        String phoneNumber
) implements AggregateRoot<Pharmacy, PharmacyId> {
    @Override
    public PharmacyId getId() {
        return pharmacyId;
    }
}

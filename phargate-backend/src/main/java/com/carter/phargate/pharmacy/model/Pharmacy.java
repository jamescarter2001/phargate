package com.carter.phargate.pharmacy.model;

import lombok.Builder;

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
) {}

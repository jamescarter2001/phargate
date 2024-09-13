package com.carter.phargate.model;

import lombok.Builder;

@Builder
public record Pharmacy(
        PharmacyId pharmacyId,
        PharmacyChainId pharmacyChainId,
        String addressLine1,
        String town,
        String county,
        String postcode,
        String phoneNumber
) {}

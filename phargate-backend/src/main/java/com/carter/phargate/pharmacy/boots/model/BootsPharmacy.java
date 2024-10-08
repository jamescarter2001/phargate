package com.carter.phargate.pharmacy.boots.model;

import com.carter.phargate.pharmacy.model.PharmacyId;
import com.carter.phargate.pharmacy.model.Pharmacy;
import com.carter.phargate.pharmacy.model.PharmacyChain;
import com.carter.phargate.pharmacy.model.PharmacyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.util.function.Function;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacy (
        @JsonProperty("Location") BootsPharmacyLocation location
) {

    public Pharmacy toPharmacy(Function<PharmacyType, PharmacyChain> pharmacyChainIdByPharmacyType) {
        return Pharmacy.builder()
                .pharmacyId(new PharmacyId(location.id()))
                .pharmacyChainId(pharmacyChainIdByPharmacyType.apply(PharmacyType.BOOTS).pharmacyChainId())
                .sourceId(location.id())
                .addressLine1(location.address().street())
                .town(location.address().town())
                .county(location.address().county())
                .postcode(location.address().postcode())
                .phoneNumber(Optional.ofNullable(location.contactDetails()).map(BootsPharmacyContactDetails::phone).orElse(null))
                .build();
    }

}

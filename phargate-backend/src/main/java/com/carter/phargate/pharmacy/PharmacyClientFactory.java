package com.carter.phargate.pharmacy;

import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyType;
import com.carter.phargate.util.http.RestClientFactory;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class PharmacyClientFactory {

    private final Function<PharmacyType, PharmacyChain> pharmacyChainByPharmacyType;

    public PharmacyClient newClient(PharmacyType pharmacyType) {
        if (pharmacyType == PharmacyType.BOOTS) {
            return new BootsPharmacyClient(RestClientFactory.newClient(), pharmacyChainByPharmacyType);
        }

        throw new IllegalArgumentException("Pharmacy type is not supported.");
    }

}

package com.carter.phargate.model;

public record PharmacyChain(PharmacyChainId pharmacyChainId, String name) {

    public static PharmacyChain of(long id, String name) {
        return new PharmacyChain(new PharmacyChainId(id), name);
    }

}

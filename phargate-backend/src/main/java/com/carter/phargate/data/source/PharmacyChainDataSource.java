package com.carter.phargate.data.source;

import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyChainId;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class PharmacyChainDataSource {

    private static final Map<PharmacyChainId, PharmacyChain> pharmacyChainByPharmacyTypeMap = new EnumMap<>(PharmacyChainId.class);

    static {
        pharmacyChainByPharmacyTypeMap.put(PharmacyChainId.BOOTS, new PharmacyChain(PharmacyChainId.BOOTS, "Boots Pharmacy"));
    }

    public PharmacyChain getPharmacyChainByPharmacyType(PharmacyChainId pharmacyChainId) {
        return pharmacyChainByPharmacyTypeMap.get(pharmacyChainId);
    }

}

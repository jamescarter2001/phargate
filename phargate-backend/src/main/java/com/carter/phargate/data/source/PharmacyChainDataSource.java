package com.carter.phargate.data.source;

import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class PharmacyChainDataSource {

    private static final Map<PharmacyType, PharmacyChain> pharmacyChainByPharmacyTypeMap = new EnumMap<>(PharmacyType.class);

    static {
        pharmacyChainByPharmacyTypeMap.put(PharmacyType.BOOTS, PharmacyChain.of(1L, "Boots Pharmacy"));
        pharmacyChainByPharmacyTypeMap.put(PharmacyType.LLOYDS, PharmacyChain.of(2L, "Lloyds Pharmacy"));
    }

    public Function<PharmacyType, PharmacyChain> getPharmacyChainByPharmacyTypeMapper() {
        return pharmacyChainByPharmacyTypeMap::get;
    }

}

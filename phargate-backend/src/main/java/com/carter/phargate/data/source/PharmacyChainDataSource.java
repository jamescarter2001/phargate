package com.carter.phargate.data.source;

import com.carter.phargate.pharmacy.model.PharmacyChain;
import com.carter.phargate.pharmacy.model.PharmacyType;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

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

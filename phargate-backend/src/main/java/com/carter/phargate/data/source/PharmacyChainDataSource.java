package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.PharmacyChainEntity;
import com.carter.phargate.data.repo.PharmacyChainRepository;
import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyChainId;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PharmacyChainDataSource {

    private final PharmacyChainRepository pharmacyChainRepository;

    public PharmacyChainDataSource(PharmacyChainRepository pharmacyChainRepository) {
        this.pharmacyChainRepository = pharmacyChainRepository;

        List<PharmacyChain> chains = ImmutableList.of(
                new PharmacyChain(PharmacyChainId.BOOTS, "Boots Pharmacy")
        );

        this.pharmacyChainRepository.saveAll(chains.stream().map(PharmacyChainEntity::fromPharmacyChain).toList());
    }

}

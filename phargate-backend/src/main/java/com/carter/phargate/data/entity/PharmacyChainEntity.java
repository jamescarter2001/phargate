package com.carter.phargate.data.entity;

import com.carter.phargate.model.PharmacyChain;
import com.carter.phargate.model.PharmacyChainId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pg_pharmacy_chains")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyChainEntity {

    @Id
    @Enumerated(EnumType.ORDINAL)
    private PharmacyChainId pharmacyChainId;
    private String name;
    private String apiUrl;

    public static PharmacyChainEntity fromPharmacyChain(PharmacyChain pharmacyChain) {
        return PharmacyChainEntity.builder()
                .pharmacyChainId(pharmacyChain.pharmacyChainId())
                .name(pharmacyChain.name())
                .build();
    }

}

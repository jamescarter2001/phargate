package com.carter.phargate.data.entity;

import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.model.PharmacyChainId;
import com.carter.phargate.model.PharmacyId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pg_pharmacies")
public class PharmacyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharmacyId;
    @Enumerated(EnumType.ORDINAL)
    private PharmacyChainId pharmacyChainId;
    private Long sourceId;
    @Column(name = "address_line_1")
    private String addressLine1;
    private String town;
    private String county;
    private String postcode;
    private String phoneNumber;

    public static PharmacyEntity fromPharmacy(Pharmacy pharmacy) {
        return PharmacyEntity.builder()
                .pharmacyChainId(pharmacy.pharmacyChainId())
                .sourceId(pharmacy.sourceId())
                .addressLine1(pharmacy.addressLine1())
                .town(pharmacy.town())
                .county(pharmacy.county())
                .postcode(pharmacy.postcode())
                .phoneNumber(pharmacy.phoneNumber())
                .build();
    }

    public Pharmacy asPharmacy() {
        return Pharmacy.builder()
                .pharmacyId(new PharmacyId(pharmacyId))
                .pharmacyChainId(pharmacyChainId)
                .sourceId(sourceId)
                .addressLine1(addressLine1)
                .town(town)
                .county(county)
                .postcode(postcode)
                .phoneNumber(phoneNumber)
                .build();
    }
}

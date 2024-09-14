package com.carter.phargate.data.entity;

import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.model.PharmacyChainId;
import com.carter.phargate.model.PharmacyId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(PharmacyEntityId.class)
@Table(name = "pg_pharmacies")
public class PharmacyEntity {
    @Id
    private long pharmacyId;
    @Id
    private long pharmacyChainId;
    @Column(name = "address_line_1")
    private String addressLine1;
    private String town;
    private String county;
    private String postcode;
    private String phoneNumber;

    public static PharmacyEntity fromPharmacy(Pharmacy pharmacy) {
        return PharmacyEntity.builder()
                .pharmacyId(pharmacy.pharmacyId().id())
                .pharmacyChainId(pharmacy.pharmacyChainId().id())
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
                .pharmacyChainId(new PharmacyChainId(pharmacyChainId))
                .addressLine1(addressLine1)
                .town(town)
                .county(county)
                .postcode(postcode)
                .phoneNumber(phoneNumber)
                .build();
    }
}

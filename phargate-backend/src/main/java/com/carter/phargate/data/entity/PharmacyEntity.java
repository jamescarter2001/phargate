package com.carter.phargate.data.entity;

import com.carter.phargate.pharmacy.model.Pharmacy;
import com.carter.phargate.pharmacy.model.PharmacyChainId;
import com.carter.phargate.pharmacy.model.PharmacyId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pg_pharmacies")
public class PharmacyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pharmacyId;
    private long pharmacyChainId;
    private long sourceId;
    @Column(name = "address_line_1")
    private String addressLine1;
    private String town;
    private String county;
    private String postcode;
    private String phoneNumber;

    public Pharmacy asPharmacy() {
        return Pharmacy.builder()
                .pharmacyId(new PharmacyId(pharmacyId))
                .pharmacyChainId(new PharmacyChainId(pharmacyChainId))
                .sourceId(sourceId)
                .addressLine1(addressLine1)
                .town(town)
                .county(county)
                .postcode(postcode)
                .phoneNumber(phoneNumber)
                .build();
    }
}

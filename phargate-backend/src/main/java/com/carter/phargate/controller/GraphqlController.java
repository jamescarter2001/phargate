package com.carter.phargate.controller;

import com.carter.phargate.data.source.PharmacyDataSource;
import com.carter.phargate.model.Pharmacy;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphqlController {

    private final PharmacyDataSource pharmacyDataSource;

    @QueryMapping
    public List<Pharmacy> pharmacies(@Argument("first") int first) {
        return pharmacyDataSource.getPharmacies(first);
    }

    @SchemaMapping
    public long id(Pharmacy pharmacy) {
        return pharmacy.pharmacyId().id();
    }

    @SchemaMapping
    public long chainId(Pharmacy pharmacy) {
        return pharmacy.pharmacyChainId().ordinal();
    }

}

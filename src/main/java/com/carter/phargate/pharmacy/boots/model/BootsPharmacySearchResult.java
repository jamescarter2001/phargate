package com.carter.phargate.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacySearchResult (
        @JsonProperty("longitude") double longitude,
        @JsonProperty("latitude") double latitude,
        @JsonProperty("offset") int offset,
        @JsonProperty("order") int order,
        @JsonProperty("radius") int radius,
        @JsonProperty("results") List<BootsPharmacy> pharmacies,
        @JsonProperty("total") int total
) {}

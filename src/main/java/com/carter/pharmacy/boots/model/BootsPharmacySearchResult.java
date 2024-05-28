package com.carter.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacySearchResult (
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("offset") Integer offset,
        @JsonProperty("order") Integer order,
        @JsonProperty("radius") Integer radius,
        @JsonProperty("results") List<BootsPharmacy> pharmacies
) {}

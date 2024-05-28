package com.carter.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacyAddress (
        @JsonProperty("administrativeArea") String administrativeArea,
        @JsonProperty("countryCode") String countryCode,
        @JsonProperty("county") String county,
        @JsonProperty("gridLocation") BootsPharmacyGridLocation gridLocation,
        @JsonProperty("locality") String locality,
        @JsonProperty("postcode") String postcode,
        @JsonProperty("street") String street,
        @JsonProperty("town") String town
) {}

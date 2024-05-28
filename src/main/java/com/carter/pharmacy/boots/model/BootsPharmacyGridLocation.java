package com.carter.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BootsPharmacyGridLocation (
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("propertyEasting") String propertyEasting,
        @JsonProperty("propertyNorthing") String propertyNorthing
) {}

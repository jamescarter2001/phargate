package com.carter.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacyLocation (
        @JsonProperty("id") Integer id,
        @JsonProperty("Address") BootsPharmacyAddress address,
        @JsonProperty("contactDetails") BootsPharmacyContactDetails contactDetails
) {}

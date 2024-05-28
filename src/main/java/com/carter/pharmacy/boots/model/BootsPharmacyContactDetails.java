package com.carter.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacyContactDetails (
        @JsonProperty("phone") String phone
) {}

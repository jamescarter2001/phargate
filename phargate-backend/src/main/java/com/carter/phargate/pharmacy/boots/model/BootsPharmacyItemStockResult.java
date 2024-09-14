package com.carter.phargate.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BootsPharmacyItemStockResult(
        @JsonProperty("stockLevels") List<BootsPharmacyStockLevel> stockLevels
) {}

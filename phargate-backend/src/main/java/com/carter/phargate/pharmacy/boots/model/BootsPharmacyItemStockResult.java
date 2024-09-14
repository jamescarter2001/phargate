package com.carter.phargate.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BootsPharmacyItemStockResult(
        @JsonProperty("stockLevels") List<BootsPharmacyStockLevel> stockLevels
) {}

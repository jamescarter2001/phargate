package com.carter.phargate.pharmacy.boots.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BootsPharmacyItemStockRequest(
        @JsonProperty("productIdList") List<String> productIdList,
        @JsonProperty("storeIdList") List<Long> storeIdList
) {}

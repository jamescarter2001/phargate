package com.carter.phargate.pharmacy.boots;

import com.carter.phargate.pharmacy.boots.model.BootsPharmacy;
import com.carter.phargate.pharmacy.boots.model.BootsPharmacySearchResult;
import com.carter.phargate.pharmacy.model.PharmacyChain;
import com.carter.phargate.pharmacy.model.PharmacyType;
import com.carter.phargate.pharmacy.model.Pharmacy;
import com.carter.phargate.pharmacy.PharmacyClient;
import com.carter.phargate.util.http.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class BootsPharmacyClient implements PharmacyClient {

    private final RestClient restClient;
    private final Function<PharmacyType, PharmacyChain> pharmacyChainIdByPharmacyType;

    public List<Pharmacy> getPharmacies() {

        List<Pharmacy> result = new ArrayList<>();

        int offset = 0;
        int total = -1;
        while (total == -1 || offset < total) {
            BootsPharmacySearchResult response = restClient.get(
                    String.format("https://www.boots.com/online/psc/search/store?from=%d", offset),
                    BootsPharmacySearchResult.class
            );

            total = response.total();

            List<BootsPharmacy> pharmacies = response.pharmacies();

            if (pharmacies != null && !pharmacies.isEmpty()) {
                result.addAll(
                        pharmacies.stream()
                        .map(bp -> bp.toPharmacy(pharmacyChainIdByPharmacyType))
                        .toList()
                );
                offset = offset + 10;
                try {
                    // Sleep to avoid hitting the Boots API rate limit (10 requests per min).
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (total != result.size()) {
            log.warn("Result size does not match expected API size! {} != {}", result.size(), total);
        }
        return result;
    }
}

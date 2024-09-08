package com.carter.phargate.data.repo;

import com.carter.phargate.pharmacy.model.Pharmacy;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.List;

@RequiredArgsConstructor
public class PharmacyRepository {

    private final Jdbi jdbi;

    public List<Pharmacy> getPharmacies() {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("""
            select * from pg_pharmacies
            """).mapTo(Pharmacy.class).collectIntoList();
        });
    }

    public void save(List<Pharmacy> pharmacies) {
        jdbi.withHandle(handle -> {
            PreparedBatch batch = handle.prepareBatch("""
                                    insert into pg_pharmacies (
                                    pharmacy_chain_id,
                                    source_id,
                                    address_line_1,
                                    town,
                                    county,
                                    postcode,
                                    phone_number
                                    )
                                    values (
                                    :pharmacyChainId,
                                    :sourceId,
                                    :addressLine1,
                                    :town,
                                    :county,
                                    :postcode,
                                    :phoneNumber
                                    )
                                    on conflict (source_id) do
                                    update set
                                    address_line_1 = :addressLine1,
                                    town = :town,
                                    county = :county,
                                    postcode = :postcode,
                                    phone_number = :phoneNumber
                                    """);

            for (Pharmacy p : pharmacies) {
                batch
                        .bind("pharmacyChainId", p.pharmacyChainId().id())
                        .bind("sourceId", p.sourceId())
                        .bind("addressLine1", p.addressLine1())
                        .bind("town", p.town())
                        .bind("county", p.county())
                        .bind("postcode", p.postcode())
                        .bind("phoneNumber", p.phoneNumber())
                        .add();
            }

            return batch.execute();
        });
    }
}

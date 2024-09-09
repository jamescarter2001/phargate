package com.carter.phargate.data.repo;

import com.carter.phargate.pharmacy.model.Pharmacy;
import com.carter.phargate.pharmacy.model.PharmacyChainId;
import com.carter.phargate.pharmacy.model.PharmacyId;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.jdbi.v3.core.statement.StatementContext;
import org.jmolecules.ddd.types.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PharmacyRepository implements Repository<Pharmacy, PharmacyId> {

    private final Jdbi jdbi;

    public List<Pharmacy> getPharmacies() {
        return getPharmacies(25, 0);
    }

    public List<Pharmacy> getPharmacies(long first) {
        return getPharmacies(first, 0);
    }

    public List<Pharmacy> getPharmacies(long first, long offset) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("""
            select * from pg_pharmacies
            limit :first offset :offset
            """)
                    .bind("first", first)
                    .bind("offset", offset)
                    .map(new PharmacyRowMapper())
                    .list();
        });
    }

    public void save(List<Pharmacy> pharmacies) {
        jdbi.useHandle(handle -> {
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

            batch.execute();
        });
    }

    static class PharmacyRowMapper implements RowMapper<Pharmacy> {

        @Override
        public Pharmacy map(ResultSet rs, StatementContext ctx) throws SQLException {
            PharmacyId pharmacyId = ctx.findColumnMapperFor(PharmacyId.class).orElseThrow().map(rs, "pharmacy_id", ctx);
            PharmacyChainId pharmacyChainId = ctx.findColumnMapperFor(PharmacyChainId.class).orElseThrow().map(rs, "pharmacy_chain_id", ctx);

            long sourceId = rs.getLong("source_id");
            String addressLine1 = rs.getString("address_line_1");
            String town = rs.getString("town");
            String county = rs.getString("county");
            String postcode = rs.getString("postcode");
            String phoneNumber = rs.getString("phone_number");

            return new Pharmacy(pharmacyId, pharmacyChainId, sourceId, addressLine1, town, county, postcode, phoneNumber);
        }

    }
}

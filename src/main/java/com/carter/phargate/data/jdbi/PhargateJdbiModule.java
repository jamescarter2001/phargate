package com.carter.phargate.data.jdbi;

import com.carter.phargate.pharmacy.model.PharmacyChainId;
import com.carter.phargate.pharmacy.model.PharmacyId;
import lombok.experimental.UtilityClass;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.codec.Codec;
import org.jdbi.v3.core.codec.CodecFactory;
import org.jdbi.v3.core.mapper.ColumnMapper;

import java.util.function.Function;

@UtilityClass
public class PhargateJdbiModule {

    public static void registerModules(Jdbi jdbi) {
        jdbi.registerCodecFactory(
                CodecFactory.builder()
                        .addCodec(PharmacyChainId.class, new PharmacyChainIdCodec())
                        .addCodec(PharmacyId.class, new PharmacyIdCodec())
                        .build()
        );
    }

    static class PharmacyChainIdCodec implements Codec<PharmacyChainId> {
        @Override
        public ColumnMapper<PharmacyChainId> getColumnMapper() {
            return (r, columnNumber, ctx) -> new PharmacyChainId(r.getLong(columnNumber));
        }

        @Override
        public Function<PharmacyChainId, Argument> getArgumentFunction() {
            return pharmacyChainId -> (position, statement, ctx) -> statement.setLong(position, pharmacyChainId.id());
        }
    }

    static class PharmacyIdCodec implements Codec<PharmacyId> {
        @Override
        public ColumnMapper<PharmacyId> getColumnMapper() {
            return (r, columnNumber, ctx) -> new PharmacyId(r.getLong(columnNumber));
        }

        @Override
        public Function<PharmacyId, Argument> getArgumentFunction() {
            return pharmacyId -> (position, statement, ctx) -> statement.setLong(position, pharmacyId.id());
        }
    }

}

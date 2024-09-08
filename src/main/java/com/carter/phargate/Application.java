package com.carter.phargate;

import com.carter.phargate.data.jdbi.PhargateJdbiModule;
import com.carter.phargate.data.liquibase.LiquibaseService;
import com.carter.phargate.data.repo.PharmacyRepository;
import com.carter.phargate.util.http.RestClientFactory;
import com.carter.phargate.pharmacy.PharmacyChainByPharmacyTypeMapper;
import com.carter.phargate.pharmacy.PharmacyClient;
import com.carter.phargate.pharmacy.boots.BootsPharmacyClient;
import com.carter.phargate.pharmacy.model.Pharmacy;
import com.carter.phargate.pharmacy.model.PharmacyChain;
import com.carter.phargate.pharmacy.model.PharmacyType;
import com.carter.phargate.util.http.RestClient;
import io.javalin.Javalin;
import org.jdbi.v3.core.Jdbi;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;
import java.util.function.Function;

public class Application {
    public static void main(String[] args) throws Exception {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("phargate");
        ds.setUser("pharmacy");
        ds.setPassword( "pharmacy");

        LiquibaseService liquibaseService = new LiquibaseService(ds);
        liquibaseService.initSchema();

        Jdbi jdbi = Jdbi.create(ds);
        PhargateJdbiModule.registerModules(jdbi);

        Function<PharmacyType, PharmacyChain> pharmacyChainByPharmacyType = PharmacyChainByPharmacyTypeMapper.getPharmacyChainByPharmacyTypeMapper();

        RestClient restClient = RestClientFactory.newClient();
        PharmacyClient bootsPharmacyClient = new BootsPharmacyClient(restClient, pharmacyChainByPharmacyType);
        List<Pharmacy> pharmacies = bootsPharmacyClient.getPharmacies();
        new PharmacyRepository(jdbi).save(pharmacies);

        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);
    }
}
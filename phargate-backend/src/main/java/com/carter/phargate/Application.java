package com.carter.phargate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        PGSimpleDataSource ds = new PGSimpleDataSource();
//        ds.setServerName("localhost");
//        ds.setDatabaseName("phargate");
//        ds.setUser("pharmacy");
//        ds.setPassword( "pharmacy");
//
//        LiquibaseService liquibaseService = new LiquibaseService(ds);
//        liquibaseService.initSchema();
//
//        Jdbi jdbi = Jdbi.create(ds);
//        PhargateJdbiModule.registerModules(jdbi);
//
//        PharmacyChainDataSource pharmacyChainDataSource = new PharmacyChainDataSource();
//
//        Function<PharmacyType, PharmacyChain> pharmacyChainByPharmacyType = pharmacyChainDataSource.getPharmacyChainByPharmacyTypeMapper();
//
//        // RestClient restClient = RestClientFactory.newRateLimitedClient(5, Duration.ofMinutes(1), true);
//        // PharmacyClient bootsPharmacyClient = new BootsPharmacyClient(restClient, pharmacyChainByPharmacyType);
//        // bootsPharmacyClient.getPharmacies();
//
//        PharmacyRepository pharmacyRepository = new PharmacyRepository(jdbi);
//        List<Pharmacy> pharmacyList = pharmacyRepository.getPharmacies();
    }
}
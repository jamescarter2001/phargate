package com.carter.phargate.config;

import com.carter.phargate.data.repo.MedicineRepository;
import com.carter.phargate.data.repo.MedicineSourceIdRepository;
import com.carter.phargate.data.source.MedicineDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public MedicineDataSource medicineDataSource(MedicineRepository medicineRepository, MedicineSourceIdRepository medicineSourceIdRepository) {
        return new MedicineDataSource(medicineRepository, medicineSourceIdRepository);
    }

}

package com.carter.phargate.config;

import com.carter.phargate.graphql.Scalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(Scalars.newIdScalar("PharmacyID"))
                .scalar(Scalars.newIdScalar("PharmacyChainID"))
                .build();
    }

}

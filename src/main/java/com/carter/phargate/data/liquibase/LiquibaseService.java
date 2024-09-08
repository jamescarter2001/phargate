package com.carter.phargate.data.liquibase;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class LiquibaseService {

    private final Liquibase liquibase;

    public LiquibaseService(DataSource dataSource) throws Exception {
        this.liquibase = new Liquibase(
                "liquibase/master.xml",
                new ClassLoaderResourceAccessor(),
                new JdbcConnection(dataSource.getConnection())
        );
    }

    public void initSchema() throws Exception {
        liquibase.update();
    }
}

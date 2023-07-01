package com.example.transactiontest.config.database.dev;

import com.example.transactiontest.config.common.YamlLoadFactory;
import com.example.transactiontest.config.database.common.AbstractDatabaseConfiguration;
import com.example.transactiontest.config.database.common.DatabaseSourceProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@PropertySources({
        /////
        @PropertySource(value = "classpath:/application.yml", factory = YamlLoadFactory.class)
        /////
})
public class DevDatabaseConfiguration extends AbstractDatabaseConfiguration {

    @Autowired
    private DatabaseSourceProperty databaseSourceProperty;

    @Bean
    public DataSource dataSource() {

        log.info("datasource init");

        List<DatabaseSourceProperty.DatabaseInfo> clusters = new ArrayList<>(databaseSourceProperty.getClusters());

        final String driverClassName = databaseSourceProperty.getDriverClassName();

        DatabaseSourceProperty.DatabaseInfo cluster = clusters.get(0);
        final String clusterName = cluster.getName();
        HikariConfig hikariConfig = makeHikariConfig(driverClassName, cluster);

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        log.info("               JdbcUrl :: {}", hikariDataSource.getJdbcUrl());
        log.info("       DriverClassName :: {}", hikariDataSource.getDriverClassName());
        log.info("  UserName && password :: {}", hikariDataSource.getUsername() + "  ..." + (hikariDataSource.getPassword() == null ? "null" : hikariDataSource.getPassword().length()));
        log.info(" init hikariDataSource :: {}", hikariDataSource.hashCode());

        return hikariDataSource;
    }

}

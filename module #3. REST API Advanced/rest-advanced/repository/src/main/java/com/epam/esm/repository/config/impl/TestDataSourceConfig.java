package com.epam.esm.repository.config.impl;

import com.epam.esm.repository.config.DataSourceConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Objects;

@Slf4j
@Configuration
@ComponentScan("com.epam.esm")
@Profile("test")
@EnableTransactionManagement
@PropertySource("classpath:../resources/application-test.properties")
public class TestDataSourceConfig implements DataSourceConfig {
    private static final String DRIVER = "spring.datasource.driver-class-name";
    private static final String URL = "spring.datasource.url";
    private static final String USER = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";

    private final Environment environment;

    public TestDataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource;
        HikariConfig config = new HikariConfig();
        log.debug("HikariConfig for TEST Profile created");

        config.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));
        config.setJdbcUrl(Objects.requireNonNull(environment.getProperty(URL)));
        config.setUsername(Objects.requireNonNull(environment.getProperty(USER)));
        config.setPassword(Objects.requireNonNull(environment.getProperty(PASSWORD)));
        config.setMaximumPoolSize(50);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
        log.debug("HikariDataSource for TEST Profile created");

        Resource initSchema = new ClassPathResource("../resources/db/h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        log.debug("SQL script for In-memory database executed");

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJDBCTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}

package com.epam.esm.configuration.datasource.impl;

import com.epam.esm.configuration.datasource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("com.epam.esm")
/*@Profile("prod")*/
<<<<<<< HEAD
<<<<<<< HEAD
@PropertySource("classpath:/application-prod.properties")
=======
@PropertySource("classpath:/database/mysql/db.properties")
>>>>>>> a5f4ed9 (refactored to multi-module structure)
=======
@PropertySource("classpath:/application-prod.properties")
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
public class ProdDataSourceConfig implements DataSourceConfig {
    private static final String DRIVER = "prod.datasource.mysql.driver";
    private static final String URL = "prod.datasource.mysql.url";
    private static final String USER = "prod.datasource.mysql.user";
    private static final String PASSWORD = "prod.datasource.mysql.password";
    private final Environment environment;

    public ProdDataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    @Bean
    @Primary
<<<<<<< HEAD
<<<<<<< HEAD
=======
    @Qualifier("prodDataSource")
>>>>>>> a5f4ed9 (refactored to multi-module structure)
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));
        dataSource.setUrl(Objects.requireNonNull(environment.getProperty(URL)));
        dataSource.setUsername(Objects.requireNonNull(environment.getProperty(USER)));
        dataSource.setPassword(Objects.requireNonNull(environment.getProperty(PASSWORD)));

        return dataSource;
    }

    @Bean
    @Primary
    public JdbcTemplate prodJDBCTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}

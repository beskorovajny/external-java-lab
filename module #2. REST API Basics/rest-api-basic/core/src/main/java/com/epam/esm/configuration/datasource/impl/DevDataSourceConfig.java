/*
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
@Profile("dev")
@PropertySource("classpath:/database/h2/db.properties")
public class DevDataSourceConfig implements DataSourceConfig {
    private static final String DRIVER = "h2.datasource.driver";
    private static final String URL = "h2.datasource.url";
    private static final String USER = "h2.datasource.user";
    private static final String PASSWORD = "h2.datasource.password";

    private final Environment environment;

    public DevDataSourceConfig(Environment environment) {
        this.environment = environment;
    }
    @Override
    @Bean
    @Qualifier("devDataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));
        dataSource.setUrl(Objects.requireNonNull(environment.getProperty(URL)));
        dataSource.setUsername(Objects.requireNonNull(environment.getProperty(USER)));
        dataSource.setPassword(Objects.requireNonNull(environment.getProperty(PASSWORD)));

        return dataSource;
    }

    @Bean
    public JdbcTemplate devJDBCTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
*/

package com.epam.esm.repository.configuration;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Configuration
@EnableTransactionManagement
@Profile("prod")
public class ProdJPAConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.jpa.show-sql}")
    private String showSql;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDDLAuto;
    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;
    @Value("${spring.jpa.properties.hibernate.highlight_sql}")
    private String highlightSql;
   /* @Value("${spring.jpa.properties.hibernate.hbm2ddl.import_files}")
    private String importFiles;*/
   /* @Value("${spring.jpa.defer-datasource-initialization}")
    private String deferDatasourceInit;*/
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
   /* @Value("${spring.sql.init.mode}")
    private String initMode;
    @Value("${spring.sql.init.platform}")
    private String initPlatform;*/
    /*@Value("${spring.sql.init.data-locations}")
    private String initialDataLocations;*/

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        log.debug("DataSource created");

        /*Resource initSchema = new ClassPathResource("/schema.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);*/
        return dataSource;
    }

    /*@Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/schema-mysql.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/data-mysql.sql"));


        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }*/

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateDDLAuto);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.highlight_sql", highlightSql);
        properties.setProperty("hibernate.dialect", dialect);
        /*properties.setProperty("hibernate.hbm2ddl.import_files", importFiles);*/
       /* properties.setProperty("spring.jpa.defer-datasource-initialization", deferDatasourceInit);*/
       /* properties.setProperty("sql.init-mode", initMode);
        properties.setProperty("sql.init-platform", initPlatform);*/
       /* properties.setProperty("init.data-locations", initialDataLocations);*/
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setDataSource(dataSource());
        emfBean.setJpaProperties(properties);
        emfBean.setPackagesToScan("com.epam.esm");
        emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        log.debug("EntityManagerFactory created");
        return emfBean;
    }

    @Bean
    public EntityManager entityManager() {
        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        log.debug("JpaTransactionManager created");
        return jpaTransactionManager;
    }


}

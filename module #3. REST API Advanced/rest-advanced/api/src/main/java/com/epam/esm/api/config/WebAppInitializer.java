package com.epam.esm.api.config;

import com.epam.esm.repository.config.impl.ProdDataSourceConfig;
import com.epam.esm.repository.config.impl.TestDataSourceConfig;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {
    private static final String PROFILES_PROD = "prod";
    private static final String PROFILES_TEST = "test";

    @Override
    public void onStartup(ServletContext servletContext) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ProdDataSourceConfig.class);
        log.debug("ProdDataSourceConfig registered in Root Context");
        rootContext.register(TestDataSourceConfig.class);
        log.debug("TestDataSourceConfig registered in Root Context");

        rootContext.getEnvironment().setActiveProfiles(PROFILES_PROD);
        log.debug("Production profile activated");

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
        log.debug("ContextLoaderListener added to Servlet Context");

    }
}

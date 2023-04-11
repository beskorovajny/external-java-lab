package com.epam.esm.configuration.initializer;

import com.epam.esm.configuration.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration;
@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String PROFILES_PROD = "prod";
    private static final String PROFILES_TEST = "test";

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        log.debug("AppConfig registered in Root Context");

        rootContext.getEnvironment().setActiveProfiles(PROFILES_PROD);
        log.debug("Production profile activated");

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
        log.debug("ContextLoaderListener added to Servlet Context");


        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(dispatcherContext));
        log.debug("[{}] servlet registered in Servlet Context", DISPATCHER_SERVLET_NAME);
        dispatcher.setLoadOnStartup(1);

        dispatcher.addMapping("/*");
    }
}
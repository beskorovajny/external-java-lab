/*
package com.epam.esm.api.config;

import com.epam.esm.jpa.config.JPAConfig;
import com.epam.esm.securityjwtimpl.config.SecurityConfig;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@ComponentScan("com.epam.esm")
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                JPAConfig.class,
                SecurityConfig.class,
                HATEOASConfig.class,
                ServletConfig.class,
                WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String @NotNull [] getServletMappings() {
        return new String[]{"/api"};
    }
}
*/

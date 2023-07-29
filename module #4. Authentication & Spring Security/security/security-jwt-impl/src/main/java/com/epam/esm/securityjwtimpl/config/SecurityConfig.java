package com.epam.esm.securityjwtimpl.config;

import com.epam.esm.core.model.enums.UserRole;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.securityjwtimpl.JwtService;
import com.epam.esm.securityjwtimpl.filter.AuthTokenFilter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@Data
public class SecurityConfig {

    @Value("${security.app.uri.certificates}")
    private String certificatesURI;

    @Value("${security.app.uri.tags}")
    private String tagsURI;

    @Value("${security.app.uri.receipts}")
    private String receiptsURI;

    @Value("${security.app.uri.users}")
    private String usersURI;

    private final UserDetailsService userDetailsService;

    private final AuthenticationEntryPoint unauthorizedHandler;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthTokenFilter authTokenFilter,
                                           AuthenticationProvider authenticationProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(POST, "/auth/**")
                                .permitAll()
                                .requestMatchers(GET, certificatesURI)
                                .permitAll()
                                .requestMatchers(POST, "/certificates/find-by-tags")
                                .permitAll()
                                .requestMatchers(GET, certificatesURI, tagsURI, usersURI, receiptsURI)
                                .hasAnyAuthority(UserRole.CUSTOMER.getRoleName(), UserRole.ADMIN.getRoleName())
                                .requestMatchers(POST, receiptsURI)
                                .hasAnyAuthority(UserRole.CUSTOMER.getRoleName(), UserRole.ADMIN.getRoleName())
                                .requestMatchers(PATCH, certificatesURI)
                                .hasAuthority(UserRole.ADMIN.getRoleName())
                                .requestMatchers(POST, certificatesURI, tagsURI)
                                .hasAuthority(UserRole.ADMIN.getRoleName())
                                .requestMatchers(DELETE, certificatesURI, tagsURI, receiptsURI)
                                .hasAuthority(UserRole.ADMIN.getRoleName())
                                .anyRequest()
                                .authenticated()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext()));

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider);

        return http.build();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(UserDetailsService userDetailsService,
                                                        JwtService jwtService,
                                                        TokenRepository tokenRepository) {
        return new AuthTokenFilter(jwtService, userDetailsService, tokenRepository);
    }


    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

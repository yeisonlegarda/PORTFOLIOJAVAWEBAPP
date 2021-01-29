/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.configuration;


import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * Configure cors for allowing app access
 * @author Yeison David Sanchez Legarda
 */
@Configuration
public class CorsConfigSecurity extends WebSecurityConfigurerAdapter {
    @Value("${security.cors.global.enable:true}")
    private boolean enableCors;
    @Value("${security.csrf.global.enable:true}")
    private boolean enableCsrf;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (enableCsrf) {
            http.csrf().disable();
        }
        if (enableCors) {
            http.cors();
        }
    }

    @Value("${security.cors.global.allowed-origins:*}")
    private List<String> allowedOrigins;
    @Value("${security.cors.global.allowed-methods:*}")
    private List<String> allowedMethods;
    @Value("${security.cors.global.allowed-headers:*}")
    private List<String> allowedHeaders;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowedHeaders(allowedHeaders);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

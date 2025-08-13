package com.nirajan.socialscape.socialscape.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated() //allow signup/login without authentication
        );

        // use http basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF(Cross Site Request Frogery)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

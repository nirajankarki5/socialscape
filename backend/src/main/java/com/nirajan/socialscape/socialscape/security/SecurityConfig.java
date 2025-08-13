package com.nirajan.socialscape.socialscape.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(configurer ->
                configurer
                        // Public endpoints (login, signup)
                        .requestMatchers("/api/auth/**").permitAll()
                        // Role-based endpoints
                        // (if you are using @PreAuthorize("hasAuthority('USER')") in rest controller, do not add endpoints here)
                        .requestMatchers("/api/events/**").hasAuthority("USER")
                        // All other endpoints require authentication
                        .anyRequest().authenticated()
        );

        // Add JWT filter
        http.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}

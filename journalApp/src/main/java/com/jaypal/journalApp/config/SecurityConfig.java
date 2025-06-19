package com.jaypal.journalApp.config;

import com.jaypal.journalApp.service.UserDetailsServiceImpl;
import com.jaypal.journalApp.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up Spring Security.
 * This class handles authentication, authorization, password encoding,
 * and integration of a custom JWT authentication filter.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtFilter;

    /**
     * Constructor-based injection for custom UserDetailsService and JWT filter.
     * Using constructor injection avoids circular dependencies.
     */
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    /**
     * Defines the password encoder bean to hash passwords securely.
     * BCrypt is a strong one-way hashing function recommended by Spring Security.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes an AuthenticationManager bean to be used in login/authentication workflows.
     * It delegates authentication operations to configured providers.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines the main security filter chain, which sets up:
     * - URL access rules
     * - AuthenticationManager integration
     * - JWT filter placement
     * - CSRF disabling (common for stateless REST APIs)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Manually configure AuthenticationManager with custom user details and password encoder
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        return http
                .authenticationManager(authBuilder.build())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()               // Open endpoints
                        .requestMatchers("/journal/**", "/user/**").authenticated() // Requires user login
                        .requestMatchers("/admin/**").hasRole("ADMIN")           // Admin-only access
                        .anyRequest().authenticated())                          // Any other requests must be authenticated
                .csrf(AbstractHttpConfigurer::disable)                           // Disable CSRF protection for APIs
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before default login filter
                .build();
    }
}

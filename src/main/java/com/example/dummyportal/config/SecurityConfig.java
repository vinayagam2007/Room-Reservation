package com.example.dummyportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration c = new CorsConfiguration();
                c.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
                c.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                c.setAllowedHeaders(List.of("*"));
                return c;
            }))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/branches/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}

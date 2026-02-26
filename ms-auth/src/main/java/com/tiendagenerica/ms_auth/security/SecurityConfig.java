package com.tiendagenerica.ms_auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desactiva CSRF porque usamos JWT, no sesiones
                .csrf(csrf -> csrf.disable())

                // Configura qué rutas son públicas y cuáles no
                .authorizeHttpRequests(auth -> auth
                        // Estas rutas no necesitan token
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/validar").permitAll()
                        // Cualquier otra ruta necesita autenticación
                        .anyRequest().authenticated()
                )

                // No usamos sesiones porque JWT es stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
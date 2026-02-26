package com.tiendagenerica.ms_auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  // ← Le dice a Spring que esta clase tiene Beans
public class PasswordEncoderConfig {

    @Bean  // ← Registra el PasswordEncoder en el contenedor de Spring
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

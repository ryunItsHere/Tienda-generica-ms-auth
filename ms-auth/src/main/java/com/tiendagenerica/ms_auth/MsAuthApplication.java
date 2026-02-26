package com.tiendagenerica.ms_auth;

import com.tiendagenerica.ms_auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsAuthApplication implements CommandLineRunner {

	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(MsAuthApplication.class, args);
	}

	// Este método se ejecuta automáticamente cuando arranca la app
	@Override
	public void run(String... args) throws Exception {
		authService.crearAdminInicial();
	}
}

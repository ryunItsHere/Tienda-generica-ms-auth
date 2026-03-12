package com.tiendagenerica.ms_auth.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponse {
	private String token;
	private String username;
	private String mensaje;

	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponse(String token, String username, String mensaje) {
		super();
		this.token = token;
		this.username = username;
		this.mensaje = mensaje;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}

package com.tiendagenerica.ms_auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String cedula;
    private String nombreCompleto;
    private String email;
    private String username;
    private String password;
}

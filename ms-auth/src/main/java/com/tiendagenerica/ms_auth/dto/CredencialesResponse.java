package com.tiendagenerica.ms_auth.dto;

import lombok.Data;

@Data
public class CredencialesResponse {
    private String username;
    private String password;  // encriptada
    private String rol;       // ADMIN o EMPLEADO
    private boolean activo;
}
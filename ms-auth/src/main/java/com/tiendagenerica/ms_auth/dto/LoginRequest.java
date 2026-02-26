package com.tiendagenerica.ms_auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
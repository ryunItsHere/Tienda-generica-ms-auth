package com.tiendagenerica.ms_auth.controller;

import com.tiendagenerica.ms_auth.dto.LoginRequest;
import com.tiendagenerica.ms_auth.dto.LoginResponse;
import com.tiendagenerica.ms_auth.dto.RegisterRequest;
import com.tiendagenerica.ms_auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarToken(@RequestParam String token) {
        return ResponseEntity.ok(authService.validarToken(token));
    }
}
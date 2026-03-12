package com.tiendagenerica.ms_auth.service;

import com.tiendagenerica.ms_auth.client.UsuarioClient;
import com.tiendagenerica.ms_auth.dto.CredencialesResponse;
import com.tiendagenerica.ms_auth.dto.LoginRequest;
import com.tiendagenerica.ms_auth.dto.LoginResponse;
import com.tiendagenerica.ms_auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        // 1. Consulta credenciales a MS-Usuarios
        CredencialesResponse credenciales =
                usuarioClient.obtenerCredenciales(
                        request.getUsername());

        // 2. Verifica que el usuario esté activo
        if (!credenciales.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        // 3. Compara la contraseña con BCrypt
        if (!passwordEncoder.matches(
                request.getPassword(),
                credenciales.getPassword())) {
            throw new RuntimeException(
                    "Contraseña incorrecta");
        }

        // 4. Genera token JWT con username Y rol
        String token = jwtUtil.generarToken(
                credenciales.getUsername(),
                credenciales.getRol()
        );

        // 5. Retorna token + datos del usuario
        return new LoginResponse(
                token,
                credenciales.getUsername(),
                credenciales.getRol(),
                "Login exitoso"
        );
    }

    public boolean validarToken(String token) {
        return jwtUtil.validarToken(token);
    }

    // Ya no necesita crearAdminInicial
    // porque MS-Usuarios maneja sus propios usuarios
    public void crearAdminInicial() {
        // MS-Usuarios crea el admin en su propia BD
        // Este método queda vacío intencionalmente
    }
}
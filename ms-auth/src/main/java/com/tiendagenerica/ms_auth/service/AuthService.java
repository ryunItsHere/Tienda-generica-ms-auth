package com.tiendagenerica.ms_auth.service;

import com.tiendagenerica.ms_auth.dto.LoginRequest;
import com.tiendagenerica.ms_auth.dto.LoginResponse;
import com.tiendagenerica.ms_auth.model.Usuario;
import com.tiendagenerica.ms_auth.repository.UsuarioRepository;
import com.tiendagenerica.ms_auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        // 1. Busca el usuario en la base de datos por username
        Usuario usuario = usuarioRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        // 2. Verifica que el usuario esté activo
        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        // 3. Verifica que la contraseña sea correcta
        if (!passwordEncoder.matches(request.getPassword(),
                usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 4. Si todo está bien, genera el token JWT
        String token = jwtUtil.generarToken(usuario.getUsername());

        // 5. Retorna el token junto con los datos básicos del usuario
        return new LoginResponse(
                token,
                usuario.getUsername(),
                "Login exitoso"
        );
    }

    public boolean validarToken(String token) {
        return jwtUtil.validarToken(token);
    }

    // Este método crea el usuario administrador inicial
    // Se llamará una sola vez al arrancar la aplicación
    public void crearAdminInicial() {

        // Solo crea el admin si no existe
        if (!usuarioRepository.existsByUsername("admininicial")) {

            Usuario admin = new Usuario();
            admin.setCedula("0000000000");
            admin.setNombreCompleto("Administrador Inicial");
            admin.setEmail("admin@tiendagenerica.com");
            admin.setUsername("admininicial");

            // La contraseña se guarda encriptada, NUNCA en texto plano
            admin.setPassword(
                    passwordEncoder.encode("admin123456")
            );
            admin.setActivo(true);

            usuarioRepository.save(admin);
            System.out.println(">>> Usuario admininicial creado correctamente");
        }
    }
}
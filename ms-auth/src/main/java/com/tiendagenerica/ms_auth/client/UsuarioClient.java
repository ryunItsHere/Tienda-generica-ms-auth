package com.tiendagenerica.ms_auth.client;

import com.tiendagenerica.ms_auth.dto.CredencialesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ms.usuarios.url}")
    private String usuariosUrl;

    public CredencialesResponse obtenerCredenciales(
            String username) {
        try {
            String url = usuariosUrl
                    + "/usuarios/credenciales/" + username;

            // Log temporal para debug
            System.out.println(">>> Llamando a: " + url);

            return restTemplate.getForObject(
                    url, CredencialesResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException(
                    "Usuario no encontrado: " + username);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al contactar MS-Usuarios: "
                            + e.getMessage());
        }
    }
}
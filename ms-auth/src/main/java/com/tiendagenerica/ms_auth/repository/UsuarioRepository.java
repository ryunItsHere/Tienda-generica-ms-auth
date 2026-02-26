package com.tiendagenerica.ms_auth.repository;

import com.tiendagenerica.ms_auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su username para el login
    Optional<Usuario> findByUsername(String username);

    // Verifica si ya existe un usuario con ese username
    boolean existsByUsername(String username);

    // Verifica si ya existe un usuario con esa cedula
    boolean existsByCedula(String cedula);
}
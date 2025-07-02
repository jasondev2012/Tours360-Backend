package com.hs.tours360.repositories.seguridad;

import com.hs.tours360.entities.seguridad.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByUsuario(String usuario);
    Optional<UsuarioEntity> findFirstByUsuario(String usuario);
}

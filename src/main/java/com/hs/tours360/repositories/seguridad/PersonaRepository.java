package com.hs.tours360.repositories.seguridad;

import com.hs.tours360.entities.seguridad.EmpresaEntity;
import com.hs.tours360.entities.seguridad.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findFirstByNumeroDocumento(String numeroDocumento);
}

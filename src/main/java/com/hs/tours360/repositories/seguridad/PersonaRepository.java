package com.hs.tours360.repositories.seguridad;

import com.hs.tours360.entities.seguridad.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}

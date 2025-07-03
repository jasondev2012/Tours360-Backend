package com.hs.tours360.repositories.seguridad;

import com.hs.tours360.entities.seguridad.AgenciaEntity;
import com.hs.tours360.entities.seguridad.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Integer> {
    Optional<AgenciaEntity> findFirstByNombreUrl(String nombreUrl);
}

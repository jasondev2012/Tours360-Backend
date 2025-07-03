package com.hs.tours360.repositories.seguridad;

import com.hs.tours360.entities.seguridad.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {
    Optional<EmpresaEntity> findFirstByRuc(String ruc);
}

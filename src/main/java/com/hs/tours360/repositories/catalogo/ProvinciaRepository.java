package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.ProvinciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, String> {
    List<ProvinciaEntity> findAllByCodigoStartingWithAndActivoTrue(String codigoDepartamento);
}

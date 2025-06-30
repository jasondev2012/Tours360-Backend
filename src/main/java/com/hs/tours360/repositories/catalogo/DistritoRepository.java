package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.DistritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistritoRepository extends JpaRepository<DistritoEntity, String> {
    List<DistritoEntity> findAllByCodigoStartingWithAndActivoTrue(String codigoProvincia);
}

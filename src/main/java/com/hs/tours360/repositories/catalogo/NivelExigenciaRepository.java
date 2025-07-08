package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.NivelExigenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NivelExigenciaRepository extends JpaRepository<NivelExigenciaEntity, Short> {
    List<NivelExigenciaEntity> findAllByActivoTrue();
}

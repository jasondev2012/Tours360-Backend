package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.CategoriaEntity;
import com.hs.tours360.entities.catalogo.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Short> {
    List<CategoriaEntity> findAllByAgenciaIdAndActivoTrue(Integer idAgencia);
}

package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.DocumentoIdentidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoIdentidadRepository extends JpaRepository<DocumentoIdentidadEntity, Short> {
    List<DocumentoIdentidadEntity> findAllByActivoTrue();
}

package com.hs.tours360.repositories.catalogo;

import com.hs.tours360.entities.catalogo.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, String> {
    List<DepartamentoEntity> findAllByActivoTrue();
}

package com.hs.tours360.repositories.carpeta;

import com.hs.tours360.entities.carpeta.ImagenEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ImagenEventoRepository extends JpaRepository<ImagenEventoEntity, BigInteger> {
    List<ImagenEventoEntity> findByEvento_IdAndActivoIsTrue(int id_evento);
}

package com.hs.tours360.repositories.carpeta;

import com.hs.tours360.entities.carpeta.ImagenDestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ImagenDestinoRepository extends JpaRepository<ImagenDestinoEntity, BigInteger> {
    List<ImagenDestinoEntity> findByDestino_IdAndActivoIsTrue(int id_destino);
}

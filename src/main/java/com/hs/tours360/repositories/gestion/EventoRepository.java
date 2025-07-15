package com.hs.tours360.repositories.gestion;

import com.hs.tours360.entities.catalogo.EstatusEntity;
import com.hs.tours360.entities.gestion.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<EventoEntity, Integer> {
}

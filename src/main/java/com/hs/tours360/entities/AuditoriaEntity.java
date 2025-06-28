package com.hs.tours360.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditoriaEntity {

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

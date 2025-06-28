package com.hs.tours360.entities;

import com.hs.tours360.entities.catalogo.EstatusEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditoriaEstatusEntity {

    @ManyToOne
    @JoinColumn(name = "idEstatus", referencedColumnName = "id")
    private EstatusEntity estatus;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

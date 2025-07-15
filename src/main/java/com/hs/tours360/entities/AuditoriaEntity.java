package com.hs.tours360.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AuditoriaEntity {

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

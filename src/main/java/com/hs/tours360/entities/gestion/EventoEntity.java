package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEstatusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "evento", schema = "gestion")
public class EventoEntity extends AuditoriaEstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idDestino", referencedColumnName = "id")
    private DestinoEntity destino;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBaseSoles;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVentaSoles;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBaseDolares;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVentaDolares;

    @Column(precision = 5, scale = 2)
    private BigDecimal descuento;

    private Boolean aplicaDescuentoGrupo;

    @Column(precision = 5, scale = 2)
    private BigDecimal descuentoGrupo;

    @Lob
    private String urlGrupoWhatsapp;
    @Lob
    private String urlRepositorio;
    private Short cantidadMinimaGrupo;
    private LocalDateTime fechaExpiraDescuento;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
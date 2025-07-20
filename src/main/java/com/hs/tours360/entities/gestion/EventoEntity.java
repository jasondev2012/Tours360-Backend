package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.catalogo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "evento", schema = "gestion")
public class EventoEntity extends AuditoriaEntity {

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
    private Boolean aplicaDescuento;

    private Boolean aplicaDescuentoGrupo;

    @Column(precision = 5, scale = 2)
    private BigDecimal descuentoGrupo;

    @Column(columnDefinition = "text")
    private String urlGrupoWhatsapp;
    @Column(columnDefinition = "text")
    private String urlGrupoTelegram;
    @Column(columnDefinition = "text")
    private String urlGrupoFacebook;
    @Column(columnDefinition = "text")
    private String urlRepositorio;
    private Short cantidadMinimaGrupo;
    private LocalDateTime fechaFinDescuento;
    private LocalDateTime fechaFinDescuentoGrupo;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Column(columnDefinition = "text")
    private String titulo;

    @Column(columnDefinition = "text")
    private String subtitulo;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(columnDefinition = "text")
    private String itinerario;

    @Column(columnDefinition = "text")
    private String terminosCondiciones;

    @Column(columnDefinition = "text")
    private String recomendaciones;

    @Column(columnDefinition = "text")
    private String incluye;

    @Column(columnDefinition = "text")
    private String noIncluye;

    @Column(columnDefinition = "text")
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "idNivelExigencia", referencedColumnName = "id")
    private NivelExigenciaEntity nivelExigencia;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigo")
    private DepartamentoEntity departamento;

    @ManyToOne
    @JoinColumn(name = "codigoProvincia", referencedColumnName = "codigo")
    private ProvinciaEntity provincia;

    @ManyToOne
    @JoinColumn(name = "codigoDistrito", referencedColumnName = "codigo")
    private DistritoEntity distrito;
}
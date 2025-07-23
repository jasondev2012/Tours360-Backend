package com.hs.tours360.dto.gestion.evento;

import com.hs.tours360.dto.carpeta.ImagenDestinoListaResponse;
import com.hs.tours360.dto.carpeta.ImagenEventoListaResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventoRequest {

    private Integer id;
    private Integer idDestino;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private BigDecimal precioBaseSoles;
    private BigDecimal precioVentaSoles;
    private BigDecimal precioBaseDolares;
    private BigDecimal precioVentaDolares;
    private Short idNivelExigencia;
    private Short idCategoria;
    private String codigoDepartamento;
    private String codigoProvincia;
    private String codigoDistrito;
    private String itinerario;
    private String terminosCondiciones;
    private String recomendaciones;
    private String incluye;
    private String noIncluye;
    private String observaciones;

    private String urlGrupoWhatsapp;
    private String urlGrupoTelegram;
    private String urlGrupoFacebook;
    private String urlRepositorio;
    private Short cantidadMinimaGrupo;
    private LocalDateTime fechaFinDescuento;
    private LocalDateTime fechaFinDescuentoGrupo;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal descuentoGrupo;
    private BigDecimal descuento;
    private Boolean aplicaDescuento;
    private Boolean aplicaDescuentoGrupo;

    private List<ImagenEventoListaResponse> imagenesEvento;
}

package com.hs.tours360.dto.gestion.destino;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DestinoRequest {
    private Integer id;
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
}

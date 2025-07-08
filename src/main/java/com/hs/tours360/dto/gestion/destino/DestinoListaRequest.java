package com.hs.tours360.dto.gestion.destino;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DestinoListaRequest {
    private Integer id;
    private String titulo;
    private BigDecimal precioVentaSoles;
    private BigDecimal precioVentaDolares;
    private Integer eventosAbiertos;
    private Integer eventosCerrados;
    private String imagenReferencia;
    private Boolean activo;
}

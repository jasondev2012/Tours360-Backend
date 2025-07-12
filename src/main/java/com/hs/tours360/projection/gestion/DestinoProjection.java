package com.hs.tours360.projection.gestion;

import java.math.BigDecimal;

public interface DestinoProjection {
    Integer getId();
    String getTitulo();
    BigDecimal getPrecioVentaSoles();
    BigDecimal getPrecioVentaDolares();
    Integer getEventosAbiertos();
    Integer getEventosCerrados();
    String getImagenReferencia();
    Boolean getActivo();
    Integer getTotal();
}
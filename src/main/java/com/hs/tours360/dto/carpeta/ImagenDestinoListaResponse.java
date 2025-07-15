package com.hs.tours360.dto.carpeta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class ImagenDestinoListaResponse {
    private BigInteger id;
    private String nombre;
    private String url;
    private Long peso;
    private Integer id_destino;
}

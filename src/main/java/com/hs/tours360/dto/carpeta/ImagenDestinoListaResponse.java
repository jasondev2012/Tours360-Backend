package com.hs.tours360.dto.carpeta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImagenDestinoListaResponse extends ImagenReponseBase {
    private BigInteger id;
    private String nombre;
    private String url;
    private Long peso;
    private Integer id_destino;
}

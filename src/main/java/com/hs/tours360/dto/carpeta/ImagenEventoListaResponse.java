package com.hs.tours360.dto.carpeta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImagenEventoListaResponse extends  ImagenReponseBase {
    private Boolean esImagenDestino;
    private String rutaImagenDestino;
}

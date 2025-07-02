package com.hs.tours360.dto.catalogo;

import lombok.Data;

@Data
public class CatalogoReponse {
    private Integer id;
    private String codigo;
    private String nombre;

    public CatalogoReponse(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public CatalogoReponse(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public CatalogoReponse(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

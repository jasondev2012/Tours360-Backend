package com.hs.tours360.entities.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "nacionalidad", schema = "catalogo")
public class NacionalidadEntity {

    @Id
    private String codigo;

    private String nombre;
    private Boolean activo;
}

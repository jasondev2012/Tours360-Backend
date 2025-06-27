package com.hs.agencia360.entities.catalogo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "nivel_exigencia", schema = "catalogo")
public class NivelExigenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String nombre;
    private Boolean activo;
}

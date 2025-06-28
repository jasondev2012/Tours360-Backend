package com.hs.tours360.entities.catalogo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "valoracion", schema = "catalogo")
public class ValoracionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String nombre;
    private Boolean activo;
}

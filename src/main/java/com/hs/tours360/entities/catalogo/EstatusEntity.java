package com.hs.tours360.entities.catalogo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "estatus", schema = "catalogo")
public class EstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String nombre;
    private Boolean activo;
}

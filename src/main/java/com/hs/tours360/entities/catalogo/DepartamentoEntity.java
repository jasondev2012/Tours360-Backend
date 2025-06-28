package com.hs.tours360.entities.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "departamento", schema = "catalogo")
public class DepartamentoEntity {

    @Id
    private String codigo;

    private String nombre;
    private Boolean activo;
}

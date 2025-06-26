package com.hs.agencia360.entities.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "distrito", schema = "catalogo")
public class DistritoEntity {

    @Id
    private String codigo;

    private String nombre;
    private Boolean activo;
}

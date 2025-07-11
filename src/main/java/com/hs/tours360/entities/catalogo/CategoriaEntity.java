package com.hs.tours360.entities.catalogo;


import com.hs.tours360.entities.seguridad.AgenciaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "categoria", schema = "catalogo")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String nombre;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "idAgencia", referencedColumnName = "id")
    private AgenciaEntity agencia;
}

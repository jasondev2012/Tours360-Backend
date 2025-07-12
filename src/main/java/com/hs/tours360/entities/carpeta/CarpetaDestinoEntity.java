package com.hs.tours360.entities.carpeta;

import com.hs.tours360.entities.gestion.DestinoEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "destino", schema = "carpeta")
public class CarpetaDestinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "idDestino", referencedColumnName = "id")
    private DestinoEntity destino;

    private String nombre;
    private Long peso;
    private Boolean activo;

}

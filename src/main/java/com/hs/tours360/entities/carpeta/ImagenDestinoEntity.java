package com.hs.tours360.entities.carpeta;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.gestion.DestinoEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@Entity
@Table(name = "imagen_destino", schema = "carpeta")
public class ImagenDestinoEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "idDestino", referencedColumnName = "id")
    private DestinoEntity destino;

    private String nombre;
    private Long peso;

}

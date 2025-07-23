package com.hs.tours360.entities.carpeta;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.entities.gestion.EventoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@Entity
@Table(name = "imagen_evento", schema = "carpeta")
public class ImagenEventoEntity extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    private EventoEntity evento;

    private String nombre;
    private Long peso;
    private Boolean esImagenDestino;
    private String rutaImagenDestino;
}

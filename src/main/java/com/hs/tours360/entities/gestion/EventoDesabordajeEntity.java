package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.catalogo.PuntoParadaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "evento_desabordaje", schema = "gestion")
public class EventoDesabordajeEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "idPuntoDesabordaje", referencedColumnName = "id")
    private PuntoParadaEntity puntoDesabordaje;
}

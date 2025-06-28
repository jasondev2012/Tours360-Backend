package com.hs.agencia360.entities.gestion;

import com.hs.agencia360.entities.AuditoriaEntity;
import com.hs.agencia360.entities.catalogo.PuntoParadaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "evento_abordaje", schema = "gestion")
public class EventoAbordajeEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "idPuntoAbordaje", referencedColumnName = "id")
    private PuntoParadaEntity puntoAbordaje;
}

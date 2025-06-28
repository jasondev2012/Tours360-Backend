package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.seguridad.PersonaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "evento_participante", schema = "gestion")
public class EventoParticipanteEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "idEventoGrupo", referencedColumnName = "id")
    private EventoGrupoEntity grupo;

    @ManyToOne
    @JoinColumn(name = "idEventoAbordaje", referencedColumnName = "id")
    private EventoAbordajeEntity eventoAbordaje;

    @ManyToOne
    @JoinColumn(name = "idEventoDesabordaje", referencedColumnName = "id")
    private EventoAbordajeEntity eventoDesbordaje;

    @ManyToOne
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    private EventoEntity evento;
}

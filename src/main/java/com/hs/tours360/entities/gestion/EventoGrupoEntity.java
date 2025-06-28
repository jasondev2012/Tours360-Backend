package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "evento_grupo", schema = "gestion")
public class EventoGrupoEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    private EventoEntity evento;
}

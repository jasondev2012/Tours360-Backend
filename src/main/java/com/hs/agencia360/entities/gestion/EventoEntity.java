package com.hs.agencia360.entities.gestion;

import com.hs.agencia360.entities.AuditoriaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "evento", schema = "gestion")
public class EventoEntity extends AuditoriaEntity{

}
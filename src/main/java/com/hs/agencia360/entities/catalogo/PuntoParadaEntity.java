package com.hs.agencia360.entities.catalogo;

import com.hs.agencia360.entities.AuditoriaEntity;
import com.hs.agencia360.entities.seguridad.AgenciaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "punto_parada", schema = "catalogo")
public class PuntoParadaEntity extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String direccion;
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "idAgencia", referencedColumnName = "id")
    private AgenciaEntity agencia;
}

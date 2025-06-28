package com.hs.agencia360.entities.seguridad;

import com.hs.agencia360.entities.AuditoriaEstatusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "usuario", schema = "seguridad")
public class UsuarioEntity extends AuditoriaEstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "id")
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "idAgencia", referencedColumnName = "id")
    private AgenciaEntity agencia;

    private String usuario;
    private String contrasenia;

}

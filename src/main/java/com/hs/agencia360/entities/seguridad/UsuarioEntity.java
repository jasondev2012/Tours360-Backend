package com.hs.agencia360.entities.seguridad;

import com.hs.agencia360.entities.catalogo.EstatusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "usuario", schema = "seguridad")
public class UsuarioEntity {

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

    @ManyToOne
    @JoinColumn(name = "idEstatus", referencedColumnName = "id")
    private EstatusEntity estatus;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

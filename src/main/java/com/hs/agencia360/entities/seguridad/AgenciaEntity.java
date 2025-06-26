package com.hs.agencia360.entities.seguridad;

import com.hs.agencia360.entities.catalogo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "agencia", schema = "seguridad")
public class AgenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    private EmpresaEntity empresa;

    @ManyToOne
    @JoinColumn(name = "idEstatus", referencedColumnName = "id")
    private EstatusEntity estatus;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

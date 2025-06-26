package com.hs.agencia360.entities.seguridad;
import com.hs.agencia360.entities.catalogo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "persona", schema = "seguridad")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String celularUno;
    private String celularDos;

    @ManyToOne
    @JoinColumn(name = "idDocumentoIdentidad", referencedColumnName = "id")
    private DocumentoIdentidadEntity documentoIdentidad;

    private String numeroDocumento;

    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "idEstatus", referencedColumnName = "id")
    private EstatusEntity estatus;

    @ManyToOne
    @JoinColumn(name = "codigoDepartamentoNacimiento", referencedColumnName = "codigo")
    private DepartamentoEntity departamentoNacimiento;

    @ManyToOne
    @JoinColumn(name = "codigoProvinciaNacimiento", referencedColumnName = "codigo")
    private ProvinciaEntity provinciaNacimiento;

    @ManyToOne
    @JoinColumn(name = "codigoDistritoNacimiento", referencedColumnName = "codigo")
    private DistritoEntity distritoNacimiento;

    @ManyToOne
    @JoinColumn(name = "codigoNacionalidad", referencedColumnName = "codigo")
    private NacionalidadEntity nacionalidad;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

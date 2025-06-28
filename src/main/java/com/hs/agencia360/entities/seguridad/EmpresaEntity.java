package com.hs.agencia360.entities.seguridad;

import com.hs.agencia360.entities.AuditoriaEstatusEntity;
import com.hs.agencia360.entities.catalogo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "empresa", schema = "seguridad")
public class EmpresaEntity extends AuditoriaEstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String tipoEmpresa;
    private String representante;
    private String dniRepresentante;
    private String direccion;
    private String telefono;
    private String email;
    private String paginaWeb;
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigo")
    private DepartamentoEntity departamento;

    @ManyToOne
    @JoinColumn(name = "codigoProvincia", referencedColumnName = "codigo")
    private ProvinciaEntity provincia;

    @ManyToOne
    @JoinColumn(name = "codigoDistrito", referencedColumnName = "codigo")
    private DistritoEntity distrito;

}

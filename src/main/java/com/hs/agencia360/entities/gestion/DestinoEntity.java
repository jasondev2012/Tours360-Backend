package com.hs.agencia360.entities.gestion;

import com.hs.agencia360.entities.AuditoriaEstatusEntity;
import com.hs.agencia360.entities.catalogo.*;
import com.hs.agencia360.entities.seguridad.AgenciaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "destino", schema = "gestion")
public class DestinoEntity extends AuditoriaEstatusEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idAgencia", referencedColumnName = "id")
    private AgenciaEntity agencia;

    private String titulo;

    @Lob
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVenta;
    
    @ManyToOne
    @JoinColumn(name = "idValoracion", referencedColumnName = "id")
    private ValoracionEntity valoracion;

    @ManyToOne
    @JoinColumn(name = "idNivelExigencia", referencedColumnName = "id")
    private NivelExigenciaEntity nivelExigencia;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private CategoriaEntity categoria;


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

package com.hs.agencia360.entities.gestion;

import com.hs.agencia360.entities.catalogo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "destino", schema = "gestion")
public class DestinoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @Lob
    private String descripcion;
    private BigDecimal precioBase;
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

    @ManyToOne
    @JoinColumn(name = "idEstatus", referencedColumnName = "id")
    private EstatusEntity estatus;

    private Integer idUsuarioAlta;
    private LocalDateTime fechaAlta;
    private Integer idUsuarioModifica;
    private LocalDateTime fechaModifica;
}

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
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal precioVenta;
    
    @ManyToOne
    @JoinColumn(name = "idValoracion", referencedColumnName = "id")
    private ValoracionEntity valoracion;

    private Integer valoracion;
    private Short nivelExigencia;
    private Short categoria;

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

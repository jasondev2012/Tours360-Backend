package com.hs.tours360.entities.gestion;

import com.hs.tours360.entities.AuditoriaEntity;
import com.hs.tours360.entities.catalogo.*;
import com.hs.tours360.entities.seguridad.AgenciaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "destino", schema = "gestion")
public class DestinoEntity extends AuditoriaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idAgencia", referencedColumnName = "id")
    private AgenciaEntity agencia;

    @Column(columnDefinition = "text")
    private String titulo;

    @Column(columnDefinition = "text")
    private String subtitulo;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(columnDefinition = "text")
    private String itinerario;

    @Column(columnDefinition = "text")
    private String terminosCondiciones;

    @Column(columnDefinition = "text")
    private String recomendaciones;

    @Column(columnDefinition = "text")
    private String incluye;

    @Column(columnDefinition = "text")
    private String noIncluye;

    @Column(columnDefinition = "text")
    private String observaciones;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBaseSoles;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVentaSoles;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBaseDolares;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVentaDolares;

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

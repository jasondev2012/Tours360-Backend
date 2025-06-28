package com.hs.tours360.entities.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "documento_identidad", schema = "catalogo")
public class DocumentoIdentidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String codigoSunat;
    private String nombreLargo;
    private String nombreCorto;
    private Short longitud;
    private String tipo;
    private Boolean longitudExacta;
    private Boolean activo;
}

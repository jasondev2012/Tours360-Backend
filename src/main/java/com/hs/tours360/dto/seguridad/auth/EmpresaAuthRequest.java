package com.hs.tours360.dto.seguridad.auth;

import lombok.Data;

@Data
public class EmpresaAuthRequest {
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
    private String codigoDepartamento;
    private String codigoProvincia;
    private String codigoDistrito;
}

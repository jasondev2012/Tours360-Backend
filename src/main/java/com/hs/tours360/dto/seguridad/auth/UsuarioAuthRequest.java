package com.hs.tours360.dto.seguridad.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioAuthRequest {
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String celularUno;
    private String celularDos;
    private Short idDocumentoIdentidad;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private String codigoDepartamentoNacimiento;
    private String codigoProvinciaNacimiento;
    private String codigoDistritoNacimiento;
    private String codigoNacionalidad;
    private String usuario;
    private String contrasenia;
}

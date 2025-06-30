package com.hs.tours360.dto.seguridad.auth;


import lombok.Data;

@Data
public class RegistroRequest {
    private EmpresaAuthRequest empresa;
    private AgenciaAuthRequest agencia;
    private UsuarioAuthRequest usuario;
}
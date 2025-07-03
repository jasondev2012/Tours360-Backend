package com.hs.tours360.dto.seguridad.auth;

import com.hs.tours360.validations.auth.ValidRegistroAuth;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


import lombok.Data;

@Data
@ValidRegistroAuth
public class RegistroRequest {

    private EmpresaAuthRequest empresa;
    private AgenciaAuthRequest agencia;
    private UsuarioAuthRequest usuario;
}
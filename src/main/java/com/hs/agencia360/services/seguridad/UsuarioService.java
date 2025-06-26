package com.hs.agencia360.services.seguridad;

import com.hs.agencia360.dto.seguridad.usuario.UsuarioRequest;
import com.hs.agencia360.dto.seguridad.usuario.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listar();
    UsuarioResponse guardar(UsuarioRequest request);
}

package com.hs.tours360.services.seguridad;

import com.hs.tours360.dto.seguridad.usuario.UsuarioRequest;
import com.hs.tours360.dto.seguridad.usuario.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listar();
    UsuarioResponse guardar(UsuarioRequest request);
}

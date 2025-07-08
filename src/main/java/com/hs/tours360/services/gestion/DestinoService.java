package com.hs.tours360.services.gestion;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import com.hs.tours360.dto.seguridad.auth.RegistroRequest;

import java.util.List;

public interface DestinoService {
    CustomResponse<Integer> registrar(DestinoRequest destinoRequest);
    CustomResponse<List<DestinoListaRequest>> listar();
}

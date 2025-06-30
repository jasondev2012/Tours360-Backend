package com.hs.tours360.services.catalogo;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.catalogo.CatalogoReponse;
import com.hs.tours360.dto.seguridad.auth.AuthRequest;

import java.util.List;

public interface CatalogoService {
    CustomResponse<List<CatalogoReponse>> listar(String catalogo, String padre);
}

package com.hs.tours360.services.gestion;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.FiltroRequest;
import com.hs.tours360.dto.PaginatedResponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import org.springframework.data.domain.Page;

public interface DestinoService {
    CustomResponse<Integer> registrar(DestinoRequest destinoRequest);
    CustomResponse<DestinoRequest> obtener(Integer id);
    CustomResponse<String> eliminar(Integer id);
    CustomResponse<PaginatedResponse<DestinoListaRequest>> listarPaginado(FiltroRequest filtro);
    CustomResponse<String> activar(Integer id);
}

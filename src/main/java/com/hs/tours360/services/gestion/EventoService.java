package com.hs.tours360.services.gestion;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.FiltroRequest;
import com.hs.tours360.dto.PaginatedResponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import com.hs.tours360.dto.gestion.evento.EventoRequest;

public interface EventoService {
    CustomResponse<Integer> registrar(EventoRequest eventoRequest);
//    CustomResponse<DestinoRequest> obtener(Integer id);
//    CustomResponse<String> eliminar(Integer id);
//    CustomResponse<PaginatedResponse<DestinoListaRequest>> listarPaginado(FiltroRequest filtro);
//    CustomResponse<String> activar(Integer id);
}

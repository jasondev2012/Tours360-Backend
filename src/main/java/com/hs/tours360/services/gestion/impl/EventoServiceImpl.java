package com.hs.tours360.services.gestion.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.gestion.evento.EventoRequest;
import com.hs.tours360.entities.catalogo.*;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.entities.gestion.EventoEntity;
import com.hs.tours360.repositories.carpeta.ImagenDestinoRepository;
import com.hs.tours360.repositories.gestion.EventoRepository;
import com.hs.tours360.services.gestion.EventoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;
    private final ImagenDestinoRepository imagenDestinoRepository;
    @Value("${app.url.base}")
    private String appUrlBase;
    EventoServiceImpl(EventoRepository eventoRepository, ImagenDestinoRepository imagenDestinoRepository) {
        this.eventoRepository = eventoRepository;
        this.imagenDestinoRepository = imagenDestinoRepository;
    }
    @Override
    public CustomResponse<Integer> registrar(EventoRequest eventoRequest) {
        CustomResponse<Integer> response = new CustomResponse<>();

        EventoEntity evento = getEventoEntity(eventoRequest, eventoRepository);

        EventoEntity eventoGuardado = eventoRepository.save(evento);
        response.setData(eventoGuardado.getId());
        response.setMessage("Evento guardado correctamente");
        return response;
    }
    private static EventoEntity getEventoEntity(EventoRequest eventoRequest, EventoRepository eventoRepository) {
        Integer idAgencia = RequestContext.getAgenciaId();
        Integer idUsuario = RequestContext.getUsuarioId();

        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setId(eventoRequest.getIdDestino());

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(eventoRequest.getIdCategoria());

        NivelExigenciaEntity nivelExigenciaEntity = new NivelExigenciaEntity();
        nivelExigenciaEntity.setId(eventoRequest.getIdNivelExigencia());

        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        departamentoEntity.setCodigo(eventoRequest.getCodigoDepartamento());

        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        provinciaEntity.setCodigo(eventoRequest.getCodigoProvincia());

        DistritoEntity distritoEntity = new DistritoEntity();
        distritoEntity.setCodigo(eventoRequest.getCodigoDistrito());

        EventoEntity evento = new EventoEntity();
        if(eventoRequest.getId() > 0){
            evento = eventoRepository.getReferenceById(eventoRequest.getId());
        }
        evento.setDestino(destinoEntity);
        evento.setCategoria(categoriaEntity);
        evento.setDepartamento(departamentoEntity);
        evento.setProvincia(provinciaEntity);
        evento.setDistrito(distritoEntity);
        evento.setNivelExigencia(nivelExigenciaEntity);
        evento.setId(eventoRequest.getId());
        evento.setTitulo(eventoRequest.getTitulo());
        evento.setSubtitulo(eventoRequest.getSubtitulo());
        evento.setDescripcion(eventoRequest.getDescripcion());
        evento.setPrecioBaseSoles(eventoRequest.getPrecioBaseSoles());
        evento.setPrecioVentaSoles(eventoRequest.getPrecioVentaSoles());
        evento.setPrecioBaseDolares(eventoRequest.getPrecioBaseDolares());
        evento.setPrecioVentaDolares(eventoRequest.getPrecioVentaDolares());

        evento.setItinerario(eventoRequest.getItinerario());
        evento.setTerminosCondiciones(eventoRequest.getTerminosCondiciones());
        evento.setRecomendaciones(eventoRequest.getRecomendaciones());
        evento.setIncluye(eventoRequest.getIncluye());
        evento.setNoIncluye(eventoRequest.getNoIncluye());
        evento.setObservaciones(eventoRequest.getObservaciones());


        if(eventoRequest.getId() == null || eventoRequest.getId() == 0) {
            evento.setFechaAlta(LocalDateTime.now());
            evento.setIdUsuarioAlta(idUsuario);
            evento.setActivo(true);
        }else{
            evento.setFechaModifica(LocalDateTime.now());
            evento.setIdUsuarioModifica(idUsuario);
        }

        evento.setUrlGrupoWhatsapp(eventoRequest.getUrlGrupoWhatsapp());
        evento.setUrlGrupoTelegram(eventoRequest.getUrlGrupoTelegram());
        evento.setUrlGrupoFacebook(eventoRequest.getUrlGrupoFacebook());
        evento.setUrlRepositorio(eventoRequest.getUrlRepositorio());
        evento.setCantidadMinimaGrupo(eventoRequest.getCantidadMinimaGrupo());
        evento.setFechaFinDescuento(eventoRequest.getFechaFinDescuento());
        evento.setFechaFinDescuentoGrupo(eventoRequest.getFechaFinDescuentoGrupo());
        evento.setFechaPublicacion(eventoRequest.getFechaPublicacion());
        evento.setFechaInicio(eventoRequest.getFechaInicio());
        evento.setFechaFin(eventoRequest.getFechaFin());
        evento.setDescuento(eventoRequest.getDescuento());
        evento.setDescuentoGrupo(eventoRequest.getDescuentoGrupo());
        evento.setAplicaDescuento(eventoRequest.getAplicaDescuento());
        evento.setAplicaDescuentoGrupo(eventoRequest.getAplicaDescuentoGrupo());
        return evento;
    }
}

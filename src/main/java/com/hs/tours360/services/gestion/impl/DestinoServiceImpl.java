package com.hs.tours360.services.gestion.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.CarpetaConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.FiltroRequest;
import com.hs.tours360.dto.PaginatedResponse;
import com.hs.tours360.dto.carpeta.ImagenDestinoListaResponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import com.hs.tours360.entities.catalogo.*;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.entities.seguridad.AgenciaEntity;
import com.hs.tours360.projection.gestion.DestinoProjection;
import com.hs.tours360.repositories.carpeta.ImagenDestinoRepository;
import com.hs.tours360.repositories.gestion.DestinoRepository;
import com.hs.tours360.services.gestion.DestinoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class DestinoServiceImpl implements DestinoService {
    private final DestinoRepository destinoRepository;
    private final ImagenDestinoRepository imagenDestinoRepository;
    @Value("${app.url.base}")
    private String appUrlBase;
    DestinoServiceImpl(DestinoRepository destinoRepository, ImagenDestinoRepository imagenDestinoRepository) {
        this.destinoRepository = destinoRepository;
        this.imagenDestinoRepository = imagenDestinoRepository;
    }
    @Override
    public CustomResponse<PaginatedResponse<DestinoListaRequest>> listarPaginado(FiltroRequest filtro) {
        Integer idAgencia = RequestContext.getAgenciaId();
        String filtroTexto = filtro.getFiltro() != null ? filtro.getFiltro() : "";

        List<DestinoProjection> resultado = destinoRepository.listarPaginado(idAgencia,
                filtroTexto,
                filtro.getOffset(),
                filtro.getLimit(),
                filtro.getOrder(),
                filtro.getOrderDirection());
        int total = resultado.isEmpty() || resultado.getFirst().getTotal() == null
                ? 0
                : resultado.getFirst().getTotal();
        List<DestinoListaRequest> contenido = resultado.stream().map(d -> new DestinoListaRequest(
                d.getId(),
                d.getTitulo(),
                d.getPrecioVentaSoles(),
                d.getPrecioVentaDolares(),
                d.getEventosAbiertos(),
                d.getEventosCerrados(),
                d.getImagenReferencia() == null ? null : appUrlBase + "/api/file/img/"+ CarpetaConstans.IMAGEN_DESTINO +"/" + d.getImagenReferencia(),
                d.getActivo()
        )).toList();

        PaginatedResponse<DestinoListaRequest> paginated = new PaginatedResponse<>(
                contenido,
                total,
                filtro.getPageIndex(),
                filtro.getPageSize()
        );

        CustomResponse<PaginatedResponse<DestinoListaRequest>> response = new CustomResponse<>();
        response.setData(paginated);
        return response;
    }
    @Override
    public CustomResponse<DestinoRequest> obtener(Integer id){
        CustomResponse<DestinoRequest> response = new CustomResponse<>();
        Integer idAgencia = RequestContext.getAgenciaId();
        Integer idUsuario = RequestContext.getUsuarioId();
        DestinoEntity destinoRef = destinoRepository.getReferenceById(id);

        if(Objects.equals(destinoRef.getIdUsuarioAlta(), idUsuario) && Objects.equals(destinoRef.getAgencia().getId(), idAgencia)){
            DestinoRequest request = new DestinoRequest();
            request.setId(destinoRef.getId());
            request.setTitulo(destinoRef.getTitulo());
            request.setSubtitulo(destinoRef.getSubtitulo());
            request.setDescripcion(destinoRef.getDescripcion());
            request.setIdCategoria(destinoRef.getCategoria().getId());
            request.setIdNivelExigencia(destinoRef.getNivelExigencia().getId());
            request.setPrecioBaseSoles(destinoRef.getPrecioBaseSoles());
            request.setPrecioVentaSoles(destinoRef.getPrecioVentaSoles());
            request.setPrecioBaseDolares(destinoRef.getPrecioBaseDolares());
            request.setPrecioVentaDolares(destinoRef.getPrecioVentaDolares());
            request.setCodigoDepartamento(destinoRef.getDepartamento().getCodigo());
            request.setCodigoProvincia(destinoRef.getProvincia().getCodigo());
            request.setCodigoDistrito(destinoRef.getDistrito().getCodigo());
            request.setItinerario(destinoRef.getItinerario());
            request.setTerminosCondiciones(destinoRef.getTerminosCondiciones());
            request.setRecomendaciones(destinoRef.getRecomendaciones());
            request.setIncluye(destinoRef.getIncluye());
            request.setNoIncluye(destinoRef.getNoIncluye());
            request.setObservaciones(destinoRef.getObservaciones());
            Integer idDestino = destinoRef.getId();
            var imagenesDestino = imagenDestinoRepository.findByDestino_IdAndActivoIsTrue(idDestino);
            if(!imagenesDestino.isEmpty()){
                request.setImagenesDestino(imagenesDestino.stream().map(lista -> new ImagenDestinoListaResponse(
                        lista.getId(),
                        lista.getNombre(),
                        appUrlBase + "/api/file/img/"+ CarpetaConstans.IMAGEN_DESTINO +"/" + lista.getDestino().getId() + "/" + lista.getNombre(),
                        lista.getPeso(),
                        lista.getDestino().getId())).toList()
                );
            }
            response.setData(request);
            response.setMessage("Se obtuvieron los datos correctamente");
        }else{
            response.setSuccess(false);
            response.setMessage("El destino que intenta actualizar no se encuentra disponible o no existe.");
        }
        return response;
    }
    @Override
    public CustomResponse<Integer> registrar(DestinoRequest destinoRequest) {
        CustomResponse<Integer> response = new CustomResponse<>();

        DestinoEntity destino = getDestinoEntity(destinoRequest, destinoRepository);

        DestinoEntity destinoGuardado = destinoRepository.save(destino);
        response.setData(destinoGuardado.getId());
        response.setMessage("Destino guardado correctamente");
        return response;
    }
    @Override
    public CustomResponse<String> eliminar(Integer id) {
        CustomResponse<String> response = new CustomResponse<>();
        Integer idAgencia = RequestContext.getAgenciaId();
        Integer idUsuario = RequestContext.getUsuarioId();
        DestinoEntity destinoRef = destinoRepository.getReferenceById(id);
        if(Objects.equals(destinoRef.getIdUsuarioAlta(), idUsuario) && Objects.equals(destinoRef.getAgencia().getId(), idAgencia)){
            destinoRef.setActivo(false);
            destinoRef.setIdUsuarioModifica(idUsuario);
            destinoRef.setFechaModifica(LocalDateTime.now());
            destinoRepository.save(destinoRef);
            response.setMessage("Destino guardado correctamente");
        }else{
            response.setSuccess(false);
            response.setMessage("No cuenta con los permisos para eliminar el destino.");
        }
        return response;
    }

    private static DestinoEntity getDestinoEntity(DestinoRequest destinoRequest, DestinoRepository destinoRepository) {
        Integer idAgencia = RequestContext.getAgenciaId();
        Integer idUsuario = RequestContext.getUsuarioId();

        AgenciaEntity agenciaEntity = new AgenciaEntity();
        agenciaEntity.setId(idAgencia);

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(destinoRequest.getIdCategoria());

        NivelExigenciaEntity nivelExigenciaEntity = new NivelExigenciaEntity();
        nivelExigenciaEntity.setId(destinoRequest.getIdNivelExigencia());

        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        departamentoEntity.setCodigo(destinoRequest.getCodigoDepartamento());

        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        provinciaEntity.setCodigo(destinoRequest.getCodigoProvincia());

        DistritoEntity distritoEntity = new DistritoEntity();
        distritoEntity.setCodigo(destinoRequest.getCodigoDistrito());

        DestinoEntity destino = new DestinoEntity();
        if(destinoRequest.getId() > 0){
            destino = destinoRepository.getReferenceById(destinoRequest.getId());
        }
        destino.setAgencia(agenciaEntity);
        destino.setCategoria(categoriaEntity);
        destino.setDepartamento(departamentoEntity);
        destino.setProvincia(provinciaEntity);
        destino.setDistrito(distritoEntity);
        destino.setNivelExigencia(nivelExigenciaEntity);
        destino.setId(destinoRequest.getId());
        destino.setTitulo(destinoRequest.getTitulo());
        destino.setSubtitulo(destinoRequest.getSubtitulo());
        destino.setDescripcion(destinoRequest.getDescripcion());
        destino.setPrecioBaseSoles(destinoRequest.getPrecioBaseSoles());
        destino.setPrecioVentaSoles(destinoRequest.getPrecioVentaSoles());
        destino.setPrecioBaseDolares(destinoRequest.getPrecioBaseDolares());
        destino.setPrecioVentaDolares(destinoRequest.getPrecioVentaDolares());

        destino.setItinerario(destinoRequest.getItinerario());
        destino.setTerminosCondiciones(destinoRequest.getTerminosCondiciones());
        destino.setRecomendaciones(destinoRequest.getRecomendaciones());
        destino.setIncluye(destinoRequest.getIncluye());
        destino.setNoIncluye(destinoRequest.getNoIncluye());
        destino.setObservaciones(destinoRequest.getObservaciones());


        if(destinoRequest.getId() == null || destinoRequest.getId() == 0) {
            destino.setFechaAlta(LocalDateTime.now());
            destino.setIdUsuarioAlta(idUsuario);
            destino.setActivo(true);
        }else{
            destino.setFechaModifica(LocalDateTime.now());
            destino.setIdUsuarioModifica(idUsuario);
        }

        return destino;
    }

}

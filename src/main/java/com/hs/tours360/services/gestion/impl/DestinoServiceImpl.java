package com.hs.tours360.services.gestion.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.catalogo.CatalogoReponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import com.hs.tours360.entities.catalogo.*;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.entities.seguridad.AgenciaEntity;
import com.hs.tours360.repositories.gestion.DestinoRepository;
import com.hs.tours360.services.gestion.DestinoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DestinoServiceImpl implements DestinoService {
    private final DestinoRepository destinoRepository;

    @Override
    public CustomResponse<List<DestinoListaRequest>> listar() {
        CustomResponse<List<DestinoListaRequest>> response = new CustomResponse<>();
        List<DestinoEntity> listado = destinoRepository.findAll();
        response.setData(listado.stream().map(destino -> new DestinoListaRequest(
                destino.getId(),
                destino.getTitulo(),
                destino.getPrecioVentaSoles(),
                destino.getPrecioVentaDolares(),
                0,
                0,
                "",
                destino.getActivo()
        )).toList());
        return response;
    }

    @Override
    public CustomResponse<Integer> registrar(DestinoRequest destinoRequest) {
        CustomResponse<Integer> response = new CustomResponse<>();

        DestinoEntity destino = getDestinoEntity(destinoRequest);

        DestinoEntity destinoGuardado = destinoRepository.save(destino);
        response.setData(destinoGuardado.getId());
        response.setMessage("Destino guardado correctamente");
        return response;
    }

    private static DestinoEntity getDestinoEntity(DestinoRequest destinoRequest) {
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
        destino.setActivo(true);
        if(destinoRequest.getId() == null || destinoRequest.getId() == 0) {
            destino.setFechaModifica(LocalDateTime.now());
            destino.setIdUsuarioModifica(idUsuario);
        }else{
            destino.setFechaAlta(LocalDateTime.now());
            destino.setIdUsuarioAlta(idUsuario);
        }

        return destino;
    }
}

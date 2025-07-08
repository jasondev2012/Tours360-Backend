package com.hs.tours360.services.catalogo.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.CatalogoConstants;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.catalogo.CatalogoReponse;
import com.hs.tours360.repositories.catalogo.*;
import com.hs.tours360.services.catalogo.CatalogoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    private final DocumentoIdentidadRepository documentoIdentidadRepository;
    private final PaisRepository paisRepository;
    private final CategoriaRepository categoriaRepository;
    private final NivelExigenciaRepository nivelExigenciaRepository;
    @Override
    public CustomResponse<List<CatalogoReponse>> listar(String catalogo, String padre) {
        CustomResponse<List<CatalogoReponse>> response = new CustomResponse<>();
        Integer idAgencia = RequestContext.getAgenciaId();

        switch (catalogo) {
            case CatalogoConstants.DEPARTAMENTO -> response = buildResponse(
                    "Departamentos encontrados",
                    departamentoRepository.findAllByActivoTrue(),
                    dep -> new CatalogoReponse(dep.getCodigo(), dep.getNombre())
            );

            case CatalogoConstants.PROVINCIA -> response = buildResponse(
                    "Provincias encontrados",
                    provinciaRepository.findAllByCodigoStartingWithAndActivoTrue(padre),
                    prov -> new CatalogoReponse(prov.getCodigo(), prov.getNombre())
            );

            case CatalogoConstants.DISTRITO -> response = buildResponse(
                    "Distritos encontrados",
                    distritoRepository.findAllByCodigoStartingWithAndActivoTrue(padre),
                    dist -> new CatalogoReponse(dist.getCodigo(), dist.getNombre())
            );

            case CatalogoConstants.DOCUMENTOS_IDENTIDAD -> response = buildResponse(
                    "Documentos de identidad encontrados",
                    documentoIdentidadRepository.findAllByActivoTrue(),
                    doc -> new CatalogoReponse((int) doc.getId(), doc.getCodigoSunat(), doc.getNombreCorto())
            );

            case CatalogoConstants.PAIS -> response = buildResponse(
                    "Países encontrados",
                    paisRepository.findAllByActivoTrue(),
                    pais -> new CatalogoReponse(pais.getCodigo(), pais.getNombre())
            );
            case CatalogoConstants.CATEGORIA_DESTINO -> response = buildResponse(
                    "Categorìas encontradas",
                    categoriaRepository.findAllByAgenciaIdAndActivoTrue(idAgencia),
                    categoria -> new CatalogoReponse((int)categoria.getId(), categoria.getNombre())
            );
            case CatalogoConstants.NIVEL_EXIGENCIA -> response = buildResponse(
                    "Nieveles encontrados",
                    nivelExigenciaRepository.findAllByActivoTrue(),
                    nivel -> new CatalogoReponse((int)nivel.getId(), nivel.getNombre())
            );
            default -> {
                response.setSuccess(false);
                response.setMessage("Catálogo no soportado");
            }
        }

        return response;
    }

    private <T> CustomResponse<List<CatalogoReponse>> buildResponse(String message, List<T> entities, Function<T, CatalogoReponse> mapper) {
        CustomResponse<List<CatalogoReponse>> response = new CustomResponse<>();
        List<CatalogoReponse> data = entities.stream().map(mapper).toList();
        response.setData(data);
        response.setSuccess(true);
        response.setMessage(message);
        return response;
    }
}
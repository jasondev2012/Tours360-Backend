package com.hs.tours360.services.catalogo.impl;

import com.hs.tours360.constants.CatalogoConstants;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.catalogo.CatalogoReponse;
import com.hs.tours360.repositories.catalogo.DepartamentoRepository;
import com.hs.tours360.repositories.catalogo.DistritoRepository;
import com.hs.tours360.repositories.catalogo.ProvinciaRepository;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import com.hs.tours360.services.catalogo.CatalogoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService {
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    public CatalogoServiceImpl(DepartamentoRepository departamentoRepository,
                               ProvinciaRepository provinciaRepository,
                               DistritoRepository distritoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }
    @Override
    public CustomResponse<List<CatalogoReponse>> listar(String catalogo, String padre){
        CustomResponse<List<CatalogoReponse>> response = new CustomResponse<>();
        switch (catalogo){
            case CatalogoConstants.DEPARTAMENTO:
                List<CatalogoReponse> departamentos = departamentoRepository.findAllByActivoTrue()
                        .stream()
                        .map(dep -> {
                            CatalogoReponse dto = new CatalogoReponse();
                            dto.setCodigo(dep.getCodigo());
                            dto.setNombre(dep.getNombre());
                            return dto;
                        })
                        .toList();

                response.setData(departamentos);
                response.setSuccess(true);
                response.setMessage("Departamentos encontrados");
                break;
            case CatalogoConstants.PROVINCIA:
                List<CatalogoReponse> provincias = provinciaRepository.findAllByCodigoStartingWithAndActivoTrue(padre)
                        .stream()
                        .map(dep -> {
                            CatalogoReponse dto = new CatalogoReponse();
                            dto.setCodigo(dep.getCodigo());
                            dto.setNombre(dep.getNombre());
                            return dto;
                        })
                        .toList();

                response.setData(provincias);
                response.setSuccess(true);
                response.setMessage("Provincias encontrados");
                break;
            case CatalogoConstants.DISTRITO:
                List<CatalogoReponse> distritos = distritoRepository.findAllByCodigoStartingWithAndActivoTrue(padre)
                        .stream()
                        .map(dep -> {
                            CatalogoReponse dto = new CatalogoReponse();
                            dto.setCodigo(dep.getCodigo());
                            dto.setNombre(dep.getNombre());
                            return dto;
                        })
                        .toList();

                response.setData(distritos);
                response.setSuccess(true);
                response.setMessage("Distritos encontrados");
                break;

            default:
                response.setSuccess(false);
                response.setMessage("Catálogo no soportado");
                break;
        }
        return response;
    }
}

package com.hs.tours360.services.seguridad.impl;

import com.hs.tours360.dto.seguridad.usuario.UsuarioRequest;
import com.hs.tours360.dto.seguridad.usuario.UsuarioResponse;
import com.hs.tours360.entities.seguridad.UsuarioEntity;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import com.hs.tours360.services.seguridad.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    @Override
    public List<UsuarioResponse> listar() {
        return repo.findAll().stream().map(a -> {
            UsuarioResponse dto = new UsuarioResponse();
            dto.id = a.getId();
            dto.nombres = a.getPersona().getNombres();
            dto.apellidos = a.getPersona().getPrimerApellido();
            dto.correo = a.getPersona().getCorreo();
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse guardar(UsuarioRequest request) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.getPersona().setNombres(request.nombres);
        usuario.getPersona().setPrimerApellido(request.apellidos);
        usuario.getPersona().setCorreo(request.correo);

        UsuarioEntity saved = repo.save(usuario);

        UsuarioResponse dto = new UsuarioResponse();
        dto.id = saved.getId();
        dto.nombres = saved.getPersona().getNombres();
        dto.apellidos = saved.getPersona().getPrimerApellido();
        dto.correo = saved.getPersona().getCorreo();

        return dto;
    }
}

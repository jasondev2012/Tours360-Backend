package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.dto.seguridad.usuario.UsuarioRequest;
import com.hs.tours360.dto.seguridad.usuario.UsuarioResponse;
import com.hs.tours360.services.seguridad.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController  {


    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public UsuarioResponse guardar(@RequestBody UsuarioRequest request) {
        return service.guardar(request);
    }
}

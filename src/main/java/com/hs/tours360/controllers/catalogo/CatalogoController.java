package com.hs.tours360.controllers.catalogo;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.catalogo.CatalogoReponse;
import com.hs.tours360.services.catalogo.CatalogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
    private final CatalogoService service;
    public CatalogoController(CatalogoService catalogoService) {
        this.service = catalogoService;
    }

    @GetMapping("/listar")
    public CustomResponse<List<CatalogoReponse>> listar(@RequestParam String catalogo, @RequestParam(required = false) String padre) {
        return service.listar(catalogo, padre);
    }
}

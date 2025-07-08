package com.hs.tours360.controllers.gestion;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.gestion.destino.DestinoListaRequest;
import com.hs.tours360.dto.gestion.destino.DestinoRequest;
import com.hs.tours360.services.gestion.DestinoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destino")
@AllArgsConstructor
public class DestinoController {
    private final DestinoService destinoService;

    @PostMapping("/registrar")
    public CustomResponse<Integer> registrar(@RequestBody DestinoRequest request) {
        return destinoService.registrar(request);
    }
    @GetMapping("/listar")
    public CustomResponse<List<DestinoListaRequest>> listar() {
        return destinoService.listar();
    }
}

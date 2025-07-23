package com.hs.tours360.controllers.gestion;


import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.gestion.evento.EventoRequest;
import com.hs.tours360.services.gestion.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evento")
@AllArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @PostMapping("/registrar")
    public CustomResponse<Integer> registrar(@RequestBody EventoRequest request) {
        return eventoService.registrar(request);
    }
//    @GetMapping("/listar-paginado")
//    public CustomResponse<PaginatedResponse<DestinoListaRequest>> listar(@ModelAttribute FiltroRequest request) {
//
//        return destinoService.listarPaginado(request);
//    }
//    @GetMapping("/obtener/{id}")
//    public CustomResponse<DestinoRequest> listar(@PathVariable Integer id) {
//
//        return destinoService.obtener(id);
//    }
//    @GetMapping("/eliminar/{id}")
//    public CustomResponse<String> eliminar(@PathVariable Integer id) {
//
//        return destinoService.eliminar(id);
//    }
//    @GetMapping("/activar/{id}")
//    public CustomResponse<String> activar(@PathVariable Integer id) {
//
//        return destinoService.activar(id);
//    }
}

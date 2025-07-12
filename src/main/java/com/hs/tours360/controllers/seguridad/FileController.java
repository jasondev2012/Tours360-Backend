package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.CarpetaConstans;
import com.hs.tours360.constants.RutasConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.services.carpeta.CarpetaService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileController {
    private final CarpetaService carpetaService;
    @GetMapping("/agencia-logo/{idAgencia}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer idAgencia, @PathVariable String filename) {
        try {
            Path filePath = Paths.get(RutasConstans.AGENCIA_LOGO).resolve(idAgencia.toString()).resolve(filename).normalize();
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/img/{codigo}/{idDestino}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String codigo, @PathVariable String idDestino, @PathVariable String filename) {
        try {
            Path filePath = null;
            switch (codigo){
                case CarpetaConstans.IMAGEN_DESTINO:
                    filePath = Paths.get(RutasConstans.DESTINO_IMAGENES).resolve(idDestino).resolve(filename).normalize();
            }
            assert filePath != null;
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("registrar")
    public CustomResponse<String> registrar(@RequestParam("codigo") String codigo,
                                            @RequestParam("idDestino") Integer idDestino,
                                            @RequestParam("files") List<MultipartFile> files) {
        CustomResponse<String> response = new CustomResponse<>();
        switch (codigo){
            case CarpetaConstans.IMAGEN_DESTINO ->
                response = carpetaService.registrarFileDestino(idDestino, files);
            case CarpetaConstans.IMAGEN_EVENTO ->
                response = carpetaService.registrarFileDestino(idDestino, files);
            default ->
                response.setMessage("No ingresó ningún código de destino.");
        }
        return response;
    }
}



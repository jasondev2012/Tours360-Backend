package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.RutasConstans;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
public class FileController {
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
}

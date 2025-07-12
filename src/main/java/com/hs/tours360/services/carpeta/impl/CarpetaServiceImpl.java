package com.hs.tours360.services.carpeta.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.RutasConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.entities.carpeta.CarpetaDestinoEntity;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.repositories.carpeta.CarpetaDestinoRepository;
import com.hs.tours360.services.carpeta.CarpetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarpetaServiceImpl implements CarpetaService {

    private final CarpetaDestinoRepository carpetaDestinoRepository;

    private static final List<String> EXTENSIONES_PERMITIDAS = Arrays.asList(".jpg", ".jpeg", ".png", ".pdf", ".docx");

    @Override
    @Transactional
    public CustomResponse<String> registrarFileDestino(Integer idDestino, List<MultipartFile> files) {
        CustomResponse<String> response = new CustomResponse<>();
        Integer idAgencia = RequestContext.getAgenciaId();

        if (files == null || files.isEmpty()) {
            response.setMessage("No se recibieron archivos.");
            response.setSuccess(false);
            return response;
        }

        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setId(idDestino);

        try {
            List<CarpetaDestinoEntity> carpetas = files.stream()
                    .map(file -> {
                        String originalFilename = file.getOriginalFilename();
                        String extension = obtenerExtension(originalFilename);

                        if (!EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase())) {
                            throw new RuntimeException("Extensión no permitida: " + extension);
                        }

                        String nuevoNombre = guardarArchivo(file, idDestino, extension);

                        CarpetaDestinoEntity carpeta = new CarpetaDestinoEntity();
                        carpeta.setDestino(destinoEntity);
                        carpeta.setNombre(nuevoNombre); // nombre UUID.ext
                        carpeta.setPeso(file.getSize());
                        carpeta.setActivo(true);
                        // carpeta.setNombreOriginal(originalFilename); // si agregas este campo
                        return carpeta;
                    })
                    .collect(Collectors.toList());

            carpetaDestinoRepository.saveAll(carpetas);
            response.setData("Archivos registrados correctamente.");
            return response;

        } catch (Exception e) {
            response.setMessage("Error al registrar archivos: " + e.getMessage());
            response.setSuccess(false);
            return response;
        }
    }

    private String guardarArchivo(MultipartFile file, Integer idDestino, String extension) {
        String nuevoNombre = UUID.randomUUID() + extension;

        Path uploadPath = Paths.get(RutasConstans.DESTINO_IMAGENES, String.valueOf(idDestino));

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(nuevoNombre);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return nuevoNombre;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo en disco: " + e.getMessage(), e);
        }
    }

    private String obtenerExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}

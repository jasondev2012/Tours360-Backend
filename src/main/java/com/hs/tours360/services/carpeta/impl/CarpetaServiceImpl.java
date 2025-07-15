package com.hs.tours360.services.carpeta.impl;

import com.hs.tours360.config.RequestContext;
import com.hs.tours360.constants.CarpetaConstans;
import com.hs.tours360.constants.RutasConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.carpeta.ImagenDestinoListaResponse;
import com.hs.tours360.entities.carpeta.ImagenDestinoEntity;
import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.repositories.carpeta.ImagenDestinoRepository;
import com.hs.tours360.services.carpeta.CarpetaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarpetaServiceImpl implements CarpetaService {

    private final ImagenDestinoRepository imagenDestinoRepository;

    private static final List<String> EXTENSIONES_PERMITIDAS = Arrays.asList(".jpg", ".jpeg", ".png", ".pdf", ".docx");
    @Value("${app.url.base}")
    private String appUrlBase;

    public CarpetaServiceImpl(ImagenDestinoRepository imagenDestinoRepository) {
        this.imagenDestinoRepository = imagenDestinoRepository;
    }

    @Override
    @Transactional
    public CustomResponse<String> registraImagenDestino(Integer idDestino, List<MultipartFile> files) {
        CustomResponse<String> response = new CustomResponse<>();
        Integer idUsuario = RequestContext.getUsuarioId();

        if (files == null || files.isEmpty()) {
            response.setMessage("No se recibieron archivos.");
            response.setSuccess(false);
            return response;
        }

        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setId(idDestino);

        try {
            List<ImagenDestinoEntity> carpetas = files.stream()
                    .map(file -> {
                        String originalFilename = file.getOriginalFilename();
                        String extension = obtenerExtension(originalFilename);

                        if (!EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase())) {
                            throw new RuntimeException("Extensión no permitida: " + extension);
                        }

                        String nuevoNombre = guardarArchivo(file, idDestino, extension);

                        ImagenDestinoEntity carpeta = new ImagenDestinoEntity();
                        carpeta.setDestino(destinoEntity);
                        carpeta.setNombre(nuevoNombre); // nombre UUID.ext
                        carpeta.setPeso(file.getSize());
                        carpeta.setActivo(true);
                        carpeta.setIdUsuarioAlta(idUsuario);
                        carpeta.setFechaModifica(LocalDateTime.now());
                        // carpeta.setNombreOriginal(originalFilename); // si agregas este campo
                        return carpeta;
                    })
                    .collect(Collectors.toList());

            imagenDestinoRepository.saveAll(carpetas);
            response.setData("Archivos registrados correctamente.");
            return response;

        } catch (Exception e) {
            response.setMessage("Error al registrar archivos: " + e.getMessage());
            response.setSuccess(false);
            return response;
        }
    }

    @Override
    public CustomResponse<List<ImagenDestinoListaResponse>> eliminarImagenDestino(String codigo, BigInteger idImagenDestino) {
        CustomResponse<List<ImagenDestinoListaResponse>> response = new CustomResponse<>();
        Integer idUsuario = RequestContext.getUsuarioId();
        try {
            var imagenDestino = imagenDestinoRepository.getReferenceById(idImagenDestino);

            if(imagenDestino.getIdUsuarioAlta().equals(idUsuario)) {
                Path rutaImagen = Paths.get(RutasConstans.DESTINO_IMAGENES, imagenDestino.getDestino().getId().toString(), imagenDestino.getNombre());
                Files.deleteIfExists(rutaImagen);
                imagenDestino.setActivo(false);
                imagenDestino.setIdUsuarioModifica(idUsuario);
                imagenDestino.setFechaModifica(LocalDateTime.now());

                imagenDestinoRepository.save(imagenDestino);

                var imagenesDestino = imagenDestinoRepository.findByDestino_IdAndActivoIsTrue(imagenDestino.getDestino().getId());
                if(!imagenesDestino.isEmpty()){
                    response.setData(imagenesDestino.stream().map(lista -> new ImagenDestinoListaResponse(
                            lista.getId(),
                            lista.getNombre(),
                            appUrlBase + "/api/file/img/"+ CarpetaConstans.IMAGEN_DESTINO +"/" + lista.getDestino().getId() + "/" + lista.getNombre(),
                            lista.getPeso(),
                            lista.getDestino().getId())).toList()
                    );
                }
                response.setMessage("Imagen eliminada correctamente.");
            }else{
                response.setMessage("No cuenta con los permisos necesarios para eliminar esta imagen.");
                response.setSuccess(false);
            }

        } catch (IOException e) {
            response.setMessage("Error al eliminar la imagen: " + e.getMessage());
            response.setSuccess(false);
        }
        return response;

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

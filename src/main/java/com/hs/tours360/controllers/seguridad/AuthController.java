package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.constants.RutasConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.*;
import com.hs.tours360.entities.seguridad.AgenciaEntity;
import com.hs.tours360.repositories.seguridad.AgenciaRepository;
import com.hs.tours360.services.seguridad.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService service;
    private final AgenciaRepository agenciaRepository;

    @PostMapping("/authenticate")
    public CustomResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return service.authenticate(request);
    }
    @PostMapping("/registro")
    public CustomResponse<Integer> registrar(@Valid @RequestBody RegistroRequest request) {
        return service.registrar(request);
    }
    @PostMapping("/registro-logo")
    public CustomResponse<String> registrarLogo(
            @RequestParam("idAgencia") Integer idAgencia,
            @RequestParam("logo") MultipartFile logo) {

        try {

            AgenciaEntity agenciaEntity = agenciaRepository.findById(idAgencia)
                    .orElseThrow(() -> new EntityNotFoundException("Agencia no encontrada"));
            // Guarda el archivo en una carpeta, por ejemplo: "uploads/"
            String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(logo.getOriginalFilename()));
            String extension = "";

            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex); // conserva la extensión original
            }

            String fileName = UUID.randomUUID() + extension;

            Path uploadPath = Paths.get(RutasConstans.AGENCIA_LOGO + idAgencia);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(logo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            agenciaEntity.setLogo(fileName);
            agenciaRepository.save(agenciaEntity);

            CustomResponse<String> response = new CustomResponse<>();
            response.setMessage("El logo de la agencia se subió correctamente.");
            response.setCode((short)200);
            response.setSuccess(true);
            return response;

        } catch (Exception e) {
            CustomResponse<String> response = new CustomResponse<>();
            response.setMessage("Error al guardar el logo, por favor, ingrese alguno cuando inicie sesión.");
            response.setCode((short)400);
            response.setSuccess(false);
            response.setData(e.getMessage());
            return response;
        }
    }
}

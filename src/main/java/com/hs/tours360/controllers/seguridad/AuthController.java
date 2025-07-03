package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.*;
import com.hs.tours360.services.seguridad.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService authService) {
        this.service = authService;
    }

    @PostMapping("/authenticate")
    public CustomResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return service.authenticate(request);
    }
    @PostMapping("/registro")
    public CustomResponse<String> registrar(@Valid @RequestBody RegistroRequest request) {
        return service.registrar(request);
    }
}

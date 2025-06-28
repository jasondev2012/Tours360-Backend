package com.hs.tours360.controllers.seguridad;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.AuthRequest;
import com.hs.tours360.dto.seguridad.auth.AuthResponse;
import com.hs.tours360.services.seguridad.AuthService;
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
    @GetMapping
    public String GET() {
        return "service.authenticate(request)";
    }
}

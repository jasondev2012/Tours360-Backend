package com.hs.tours360.services.seguridad.impl;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.AuthRequest;
import com.hs.tours360.dto.seguridad.auth.AuthResponse;
import com.hs.tours360.dto.seguridad.auth.RegistroRequest;
import com.hs.tours360.entities.seguridad.UsuarioEntity;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import com.hs.tours360.services.seguridad.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepo;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthServiceImpl(UsuarioRepository repo, JwtServiceImpl jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = repo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomResponse<AuthResponse> authenticate(AuthRequest request) {
        CustomResponse<AuthResponse> response = new CustomResponse<AuthResponse>();

        UsuarioEntity usuario = usuarioRepo.findByUsuario(request.usuario).orElse(null);
        if (usuario == null) {
            response.setSuccess(false);
            response.setMessage("Usuario no encontrado");
            return response;
        }
        if (!passwordEncoder.matches(request.contrasenia, usuario.getContrasenia())) {
            response.setSuccess(false);
            response.setMessage("Usuario y/o contraseña inválida");
            return response;
        }

        AuthResponse data = jwtService.generateToken(usuario);
        response.setSuccess(true);
        response.setData(data);
        response.setMessage("Autenticación exitosa");
        return response;
    }

    @Override
    public CustomResponse<String> registrar(RegistroRequest registroRequest) {
        CustomResponse<String> response = new CustomResponse<>();
        response.setSuccess(false);
        response.setCode((short)400);
        response.setMessage("Usuario no registrado");
        return response;
    }
}

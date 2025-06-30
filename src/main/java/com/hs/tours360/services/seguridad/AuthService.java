package com.hs.tours360.services.seguridad;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.AuthRequest;
import com.hs.tours360.dto.seguridad.auth.AuthResponse;
import com.hs.tours360.dto.seguridad.auth.RegistroRequest;

public interface AuthService {
    CustomResponse<AuthResponse> authenticate(AuthRequest authRequest);
    CustomResponse<String> registrar(RegistroRequest registroRequest);
}

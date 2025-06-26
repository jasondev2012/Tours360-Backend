package com.hs.agencia360.services.seguridad;

import com.hs.agencia360.dto.CustomResponse;
import com.hs.agencia360.dto.seguridad.auth.AuthRequest;
import com.hs.agencia360.dto.seguridad.auth.AuthResponse;

public interface AuthService {
    CustomResponse<AuthResponse> authenticate(AuthRequest authRequest);
}

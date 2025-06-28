package com.hs.tours360.dto.seguridad.auth;

import java.time.LocalDateTime;
import java.util.Date;

public class AuthResponse {
    public String token;
    public LocalDateTime expira;
    public String fullname;
    public String usuario;
    public String agencia;

}
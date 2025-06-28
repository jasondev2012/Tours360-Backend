package com.hs.tours360.services.seguridad.impl;

import com.hs.tours360.dto.seguridad.auth.AuthResponse;
import com.hs.tours360.entities.seguridad.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl {

    private static final String SECRET_KEY = "proye-ctodeja-son-paral-ages-tionde-co-gios1-2025062422";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public AuthResponse generateToken(UsuarioEntity user) {
        AuthResponse authResponse = new AuthResponse();
        LocalDateTime expiracion = LocalDateTime.now().plusHours(10); // 10 horas

        Date expirationDate = Date.from(expiracion.atZone(ZoneId.systemDefault()).toInstant());
        authResponse.expira = expiracion;
        authResponse.fullname = String.format("%s %s %s",
                user.getPersona().getNombres(),
                user.getPersona().getPrimerApellido(),
                user.getPersona().getSegundoApellido());
        authResponse.usuario = user.getUsuario();
        authResponse.agencia = user.getAgencia().getNombreUrl();

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", user.getPersona().getNombres());
        claims.put("rol", user.getRol().getNombre());
        claims.put("idAgencia", user.getAgencia().getId());
        claims.put("urlAgencia", user.getAgencia().getNombreUrl());
        claims.put("email", user.getUsuario());

        authResponse.token = Jwts.builder()
                .setSubject(user.getUsuario())
                .setId(user.getId().toString())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        return authResponse;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<io.jsonwebtoken.Claims, T> claimsResolver) {
        final var claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final var expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}

package com.hs.tours360.config;

import com.hs.tours360.services.seguridad.impl.JwtServiceImpl;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UsuarioRepository usuarioRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        // Omitir rutas públicas
        if (path.startsWith("/api/auth/") || path.startsWith("/api/catalogo/auth-listar") || path.startsWith("/api/file/agencia-logo") || path.startsWith("/api/file/img")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        final Integer idAgencia;
        final Integer idUsuario;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("No ha iniciado sesión");
            return;
        }

        jwt = authHeader.substring(7);
        try {
            username = jwtService.extractUsername(jwt);
            idAgencia = jwtService.extractClaim(jwt, claims -> claims.get("idAgencia", Integer.class));
            RequestContext.setAgenciaId(idAgencia);
        } catch (Exception e) {
            // Token inválido (mal formado, firmado incorrectamente, etc.)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido o expirado");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var usuario = usuarioRepo.findByUsuario(username).orElse(null);

            if (usuario != null && jwtService.isTokenValid(jwt, usuario.getUsuario())) {
                RequestContext.setUsuarioId(usuario.getId());
                var authToken = new UsernamePasswordAuthenticationToken(
                        usuario, null, null
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o usuario no coincide");
                return;
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            RequestContext.clear();
        }
    }

}

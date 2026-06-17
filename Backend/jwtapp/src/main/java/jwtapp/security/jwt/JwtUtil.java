package jwtapp.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.clave.secreta}")
    private String claveSecreta;

    @Value("${jwt.expiration.time}")
    private String expirationTime;

    // Genera la clave de firma a partir del string del properties
    public Key obtenerClaveFirma() {
        return Keys.hmacShaKeyFor(claveSecreta.getBytes(StandardCharsets.UTF_8));
    }

    // Genera un token JWT con el correo como "sujeto"
    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
                .signWith(obtenerClaveFirma(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae el correo (subject) guardado dentro del token
    public String obtenerUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(obtenerClaveFirma())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Retorna true si el token es válido, false si está expirado o manipulado
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(obtenerClaveFirma())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

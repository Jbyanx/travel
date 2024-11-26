package com.unimag.travel.security.jwt;

import com.unimag.travel.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date tiempoActual = new Date();
        Date expiracion = new Date(tiempoActual.getTime() * ConstantesSeguridad.JWT_EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiracion)
                .signWith(SignatureAlgorithm.HS512,ConstantesSeguridad.JWT_SECRET)
                .compact();
        return token;
    }

    //Metodo para extraer el username (correo electronico) a partir de un token
    public String obtenerUsernameDeJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantesSeguridad.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Token invalido");
        }
    }
}

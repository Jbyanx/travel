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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Extraer roles del usuario autenticado
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date tiempoActual = new Date();
        Date expiracion = new Date(tiempoActual.getTime() * ConstantesSeguridad.JWT_EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles) // Agrega los roles
                .claim("idCliente", userDetails.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiracion)
                .signWith(SignatureAlgorithm.HS512,ConstantesSeguridad.JWT_SECRET)
                .compact();

    }

    //Metodo para extraer el username (correo electronico) a partir de un token
    public String obtenerUsernameDeJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantesSeguridad.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public List<String> obtenerRolesDeJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantesSeguridad.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", List.class);
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

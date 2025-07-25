package com.vdart.vdartcourses.security;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    public static final String SECRET_KEY_STRING = "8pwfyF83EUvKXfFQO7CBcqjUeoJl5VjP";
    public final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(UserDetails userDetails) {
        // Implementation for generating JWT token
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .claim("roles", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .toList())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
            .signWith(SECRET_KEY,Jwts.SIG.HS256)
            .compact();
    }
    public boolean validateToken(String token, UserDetails userDetails) {
    String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
}

private boolean isTokenExpired(String token) {
    Date expiration = Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration();
    return expiration.before(new Date());
}


    public String extractUsername(String token){
        return Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }
    public List<String> extractRoles(String token) {
        return Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("roles", List.class);
}

}

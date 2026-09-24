package com.alexandregomez.dividaapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    public boolean isTokenValid(String token) {

        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception exception) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
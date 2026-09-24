package com.alexandregomez.dividaapi.service;

import com.alexandregomez.dividaapi.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class AuthService {

    @Value("${security.client-id}")
    private String clientId;

    @Value("${security.client-secret}")
    private String clientSecret;

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    public boolean validateCredentials(AuthRequest request) {
        return clientId.equals(request.getClientId())
                && clientSecret.equals(request.getClientSecret())
                && username.equals(request.getUsername())
                && password.equals(request.getPassword());
    }

    public String generateToken(AuthRequest request) {

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .subject(request.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }
}

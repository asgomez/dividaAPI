package com.alexandregomez.dividaapi.controller;

import com.alexandregomez.dividaapi.dto.AuthRequest;
import com.alexandregomez.dividaapi.dto.AuthResponse;
import com.alexandregomez.dividaapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request) {

        if (!authService.validateCredentials(request)) {
            return ResponseEntity.status(401).build();
        }

        String token = authService.generateToken(request);

        return ResponseEntity.ok(
                new AuthResponse(token, "Bearer")
        );
    }
}
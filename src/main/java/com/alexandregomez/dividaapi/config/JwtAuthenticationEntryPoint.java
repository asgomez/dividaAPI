package com.alexandregomez.dividaapi.config;

import com.alexandregomez.dividaapi.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ErrorResponse errorResponse = new ErrorResponse(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Token inválido ou expirado",
                LocalDateTime.now()
        );

        response.getWriter().write(
                """
                {
                    "status": 401,
                    "message": "Token inválido ou expirado",
                    "timestamp": "%s"
                }
                """.formatted(errorResponse.getTimestamp())
        );
    }
}
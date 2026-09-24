package com.alexandregomez.dividaapi.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
}

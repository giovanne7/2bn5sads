package com.jwt.elliel.tentativa.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}
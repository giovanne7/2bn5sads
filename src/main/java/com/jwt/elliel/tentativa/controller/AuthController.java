package com.jwt.elliel.tentativa.controller;

import com.jwt.elliel.tentativa.dto.AuthRequest;
import com.jwt.elliel.tentativa.dto.AuthResponse;
import com.jwt.elliel.tentativa.dto.RegisterRequest;
import com.jwt.elliel.tentativa.repository.ContaRepositorio;
import com.jwt.elliel.tentativa.security.TokenHelper;
import com.jwt.elliel.tentativa.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ContaRepositorio repo;
    private final PasswordEncoder encoder;
    private final TokenHelper tokenHelper;
    private final ContaService contaService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return contaService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        var user = repo.findByEmail(request.getEmail()).orElseThrow();
        if (encoder.matches(request.getSenha(), user.getSenha())) {
            return new AuthResponse(tokenHelper.generateToken(user.getEmail()));
        }
        throw new RuntimeException("Informações Incorretas!!!");
    }
}

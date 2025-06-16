package com.jwt.elliel.tentativa.service;

import com.jwt.elliel.tentativa.dto.AuthRequest;
import com.jwt.elliel.tentativa.dto.AuthResponse;
import com.jwt.elliel.tentativa.dto.RegisterRequest;
import com.jwt.elliel.tentativa.model.Conta;
import com.jwt.elliel.tentativa.repository.ContaRepositorio;
import com.jwt.elliel.tentativa.security.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepositorio repo;
    private final PasswordEncoder encoder;
    private final TokenHelper tokenHelper;

    public String register(RegisterRequest request) {
        var user = Conta.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(encoder.encode(request.getSenha()))
                .role(request.getRole())
                .build();
        repo.save(user);
        return "Usuário cadastrado com sucesso";
    }

    public AuthResponse login(AuthRequest request) {
        var user = repo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email não encontrado"));
        if (!encoder.matches(request.getSenha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        String token = tokenHelper.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
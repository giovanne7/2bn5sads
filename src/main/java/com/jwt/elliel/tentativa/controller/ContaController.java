package com.jwt.elliel.tentativa.controller;

import com.jwt.elliel.tentativa.model.Conta;
import com.jwt.elliel.tentativa.repository.ContaRepositorio;
import com.jwt.elliel.tentativa.security.CustomContaDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContaController {

    private final ContaRepositorio repo;

    @GetMapping("/admin/users")
    public List<Conta> listarUsuarios() {
        return repo.findAll();
    }

    @GetMapping("/user/me")
    public Conta meuPerfil(@AuthenticationPrincipal CustomContaDetails userDetails) {
        return userDetails.getConta();
    }

    @PutMapping("/user/me")
    public Conta atualizarPerfil(@AuthenticationPrincipal CustomContaDetails userDetails,
                                 @RequestBody Conta novo) {
        var user = userDetails.getConta();
        user.setNome(novo.getNome());
        repo.save(user);
        return user;
    }

    @DeleteMapping("/admin/user/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        repo.deleteById(id);
        return "Usuário deletado";
    }
}
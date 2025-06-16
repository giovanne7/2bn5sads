package com.jwt.elliel.tentativa.repository;


import com.jwt.elliel.tentativa.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepositorio extends JpaRepository<Conta, Long> {
    Optional<Conta> findByEmail(String email);
}
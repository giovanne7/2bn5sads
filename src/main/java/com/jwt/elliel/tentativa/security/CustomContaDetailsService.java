package com.jwt.elliel.tentativa.security;

import com.jwt.elliel.tentativa.repository.ContaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomContaDetailsService implements UserDetailsService {

    private final ContaRepositorio repo;

    @Override
    public CustomContaDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .map(CustomContaDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
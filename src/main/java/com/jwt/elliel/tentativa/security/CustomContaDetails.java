package com.jwt.elliel.tentativa.security;

import com.jwt.elliel.tentativa.model.Conta;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomContaDetails implements UserDetails {

    private final Conta conta;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + conta.getRole()));
    }

    @Override
    public String getPassword() {
        return conta.getSenha();
    }

    @Override
    public String getUsername() {
        return conta.getEmail();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public Conta getConta() {
        return conta;
    }
}
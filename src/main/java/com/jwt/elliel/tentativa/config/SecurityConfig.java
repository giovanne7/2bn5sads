package com.jwt.elliel.tentativa.config;

import com.jwt.elliel.tentativa.security.CustomContaDetailsService;
import com.jwt.elliel.tentativa.security.JwtFiltro;
import com.jwt.elliel.tentativa.security.TokenHelper;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenHelper tokenHelper;
    private final CustomContaDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            org.springframework.security.config.annotation.web.builders.HttpSecurity http
    ) throws Exception {
        http
                .csrf().disable()
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // libera total de acesso a /auth/**:
                        .requestMatchers("/auth/**").permitAll()
                        // só ADMIN:
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // qualquer usuário autenticado:
                        .requestMatchers("/user/**").authenticated()
                        // todo o resto:
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new JwtFiltro(tokenHelper, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}

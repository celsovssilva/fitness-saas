package com.example.fitness_saas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // IMPORT CORRETO
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/aluno/cadastrar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/buscar/{id}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.PUT, "/api/aluno/atualizar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/aluno/delete/{id}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.POST,"/api/personal/cadastrar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.PUT,"/api/personal/atualizar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/personal/buscar/{id}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/personal/delete/{id}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.POST,"/api/exercicio/cadastrar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/exercicio/buscar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/exercicio/delete").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.POST,"/api/avaliacaofisica/cadastrar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/avaliacaofisica/aluno/{alunoId}/historico").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/avaliacaofisica/aluno/{alunoId}/ultima").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/avaliacaofisica/aluno/{alunoId}/comparar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.DELETE,"/api/avaliacaofisica/delete/{id}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/avaliacaofisica/{id}/pdf").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/avaliacaofisica/importarexcel/{alunoId}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.POST,"/api/treino/cadastrar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/treino/buscar/{idAluno}").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.PUT,"/api/treino/atualizar").hasAnyRole("ADMIN","PERSONAL")
                        .requestMatchers(HttpMethod.GET,"/api/treino/{id}/pdf").authenticated()
                        .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
        @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
        }

        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        }
}
package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.dto.LoginDTO;
import com.example.fitness_saas.response.LoginResponse;
import com.example.fitness_saas.security.jwt.JwtUtils;
import com.example.fitness_saas.service.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthServiceIMPL implements AuthService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        try {
            //cria mecanismo de credencial
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.senha());
            //prepara mecanismo para authenticação
            Authentication authentication = authenticationManager.authenticate(user);
            //buscar user logado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);
            LoginResponse loginResponse = new LoginResponse(token);

        } catch (BadCredentialsException e) {
            System.out.println("erro de login" + e);
        }
        return
    }

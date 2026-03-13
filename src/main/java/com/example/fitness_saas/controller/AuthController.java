package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.AuthDTO;

import com.example.fitness_saas.service.TokenService;
import  org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Validated AuthDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(),dto.senha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
       User user = (User) authentication.getPrincipal();
       var token = tokenService.gerarToken(user);
       return ResponseEntity.ok(token);
    }


}

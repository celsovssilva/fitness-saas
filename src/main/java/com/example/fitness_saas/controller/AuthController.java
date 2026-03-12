package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.AuthDTO;
import com.example.fitness_saas.dto.UserDTO;
import com.example.fitness_saas.entity.Aluno;
import com.example.fitness_saas.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Validated AuthDTO dto) {

        var authentication = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authenticate = authenticationManager.authenticate(authentication);
        var token = tokenService.gerarToken((UserDTO) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }


}

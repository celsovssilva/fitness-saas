package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.LoginDTO;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.response.LoginResponse;
import com.example.fitness_saas.security.jwt.JwtUtils;
import com.example.fitness_saas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Validated LoginDTO loginDTO ){
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.userName(),loginDTO.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = jwtUtils.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}

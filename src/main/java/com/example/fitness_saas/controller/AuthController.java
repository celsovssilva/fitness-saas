package com.example.fitness_saas.controller;

import com.example.fitness_saas.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO ){

        return ResponseEntity.ok().build();
    }
}

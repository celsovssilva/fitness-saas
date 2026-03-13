package com.example.fitness_saas.service.IMPL;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fitness_saas.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenServiceIMPL implements  TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.expiration-hours}")
    private int expirationHours;
    @Value("${api.security.token.expiration-minutes}")
    private int expirationMinutes;

    @Override
    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationHours);
           return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getUsername())
                   .withExpiresAt(expiryDate)
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token",e);
        }


    }

    @Override
    public String getSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    @Override
    public String gerarTokenRecuperacao(User user) {



        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationMinutes);

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getUsername())
                    .withClaim("tipo","recuperação")
                    .withExpiresAt(expiryDate)
                    .sign(algorithm);

        }catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token",e);
        }

    }
}

package com.example.fitness_saas.service.IMPL;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenServiceIMPL implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.issuer}")
    private String issuer;


    @Override
    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
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
}

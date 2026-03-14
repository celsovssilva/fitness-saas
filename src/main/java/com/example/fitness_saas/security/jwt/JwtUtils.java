package com.example.fitness_saas.security.jwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;  // Import necessário para Keys
import io.jsonwebtoken.io.Decoders;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyStoreSpi;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${api.security.token.secret}")
    private String jwt;

    @Value("${api.security.token.expiration-hours}")
    private int jwtExpiration;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();
    }
    public Key getSigninKey(){
       SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwt));
       return key;
    }
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token);

        } catch (MalformedJwtException e) {
            System.out.println("Token inválido" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado" + e.getMessage());
        }
        return false;
    }
}

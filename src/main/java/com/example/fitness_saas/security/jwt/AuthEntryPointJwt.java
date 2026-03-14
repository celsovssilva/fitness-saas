package com.example.fitness_saas.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//metodo de acesso, caso não seja permitido login cai aqui
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);// resposta em json
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// define a requisição 401

        final Map<String, Object> responseObj = new HashMap<>();// cria um mapa com os dados da resposta
        responseObj.put("status", HttpServletResponse.SC_UNAUTHORIZED);// mostra o erro
        responseObj.put("error", "Unauthorized");// mensagem
        //converte o mapa em json e mostra na tela
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseObj);

    }
}

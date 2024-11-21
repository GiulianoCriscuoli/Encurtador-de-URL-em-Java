package com.projeto.urlencurtada;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
      
        // A variável body recebe o que vem no corpo da requisição e faz um casting para string;
        String body = input.get("body").toString();

        // Vai receber um map com chave string e valor string 
        Map<String, String> bodyMap;

        try {      

            // converte o map para um objeto, da string recebida no body, que contém a requisição

            bodyMap = objectMapper.readValue(body, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao receber o objeto JSON da requisição: " +  e.getMessage(), e);
        }


        // // variável da url recebida no corpo do map e o tempo de expiração da url.

        // String url = bodyMap.get("originalUrl");
        // String expirationTime = bodyMap.get("expirationTime");

        String shortUrl = UUID.randomUUID().toString().substring(0, 8); // gera um valor aleatório de string com 8 caracteres

        Map<String, String> response = new HashMap<>(); // é um map de response da url encurtada

        response.put("code", shortUrl);

        return response;

    }
}
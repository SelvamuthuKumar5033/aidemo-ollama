package com.kgisl.aidemo.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class HelloService {
    private final RestTemplate restTemplate;

    @Value("${spring.ai.ollama.base-url}")
    private String ollamaApiUrl;

    @Value("${spring.ai.ollama.chat.option.model}")
    private String model;

    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getResponse(String input) {

        System.out.println("ollamaApiUrl : " + ollamaApiUrl);

        String requestPayload = String.format(
                "{ \"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false }",
                model, input);
        System.out.println(requestPayload);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the JSON payload and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(ollamaApiUrl + "/api/generate", HttpMethod.POST,
                requestEntity,
                String.class);
        // System.out.println(response);

        String responseBody = response.getBody();

        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

        // Extract the "response" field
        String result = jsonObject.get("response").getAsString();

        // Print the response value
        System.out.println(result);
        return result;
    }
}
